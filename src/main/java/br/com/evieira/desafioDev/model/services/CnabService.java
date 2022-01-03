package br.com.evieira.desafioDev.model.services;

import br.com.evieira.desafioDev.model.entities.Cnab;
import br.com.evieira.desafioDev.model.repositories.CnabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CnabService {

    @Autowired
    private CnabRepository cnabRepository;

    private List<Integer> entradas = Arrays.asList(new Integer[] {1, 4, 5, 6, 7, 8});

    public void read(final InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        final List<Cnab> cnabs = new ArrayList<>();

        while(reader.ready()) {
            cnabRepository.save(this.getDataFile(reader.readLine()));
        }
    }

    private Cnab getDataFile(final String line) {
        Integer tipo = Integer.parseInt(line.substring(0 ,1));
        String data = line.substring(1,9);
        Float valor = Float.parseFloat(line.substring(9,19)) / new Float(100.00);
        String cpf = line.substring(19, 30);
        String cartao = line.substring(30, 42);
        String hora = line.substring(42,48);
        String dono = line.substring(48, 62);
        String loja = line.substring(62, line.length()).trim();

        LocalDateTime dataHora = LocalDateTime.now()
                .withYear(Integer.parseInt(data.substring(0, 4)))
                .withMonth(Integer.parseInt(data.substring(5, 6)))
                .withDayOfMonth(Integer.parseInt(data.substring(7, 8)))
                .withHour(Integer.parseInt(hora.substring(1 ,2)))
                .withMinute(Integer.parseInt(hora.substring(3, 4)))
                .withSecond(Integer.parseInt(hora.substring(5, 6)));

        return new Cnab(tipo, dataHora, valor, cpf, cartao, dono, loja);
    }

    public List<Map> getAll() {
        Iterable<Cnab> cnabs = cnabRepository.findAll(Sort.by("nmLoja"));
        return this.groupTransactions(cnabs);
    }

    public List<Map> getAllByLoja(final String loja) {
        Iterable<Cnab> cnabs = cnabRepository.findByNmLojaContainingIgnoreCase(loja);
        return this.groupTransactions(cnabs);
    }

    private List<Map> groupTransactions(Iterable<Cnab> cnabs) {
        List<Map> lojas = new ArrayList<>();
        List<Map> transactions = new ArrayList<>();
        Map<String, Object> transaction = new HashMap<>();
        String nmLoja = "";
        Map<String, Object> loja = new HashMap<>();
        Float saldo = new Float(0);
        boolean hasLoja = false;
        for (Cnab cnab : cnabs) {
            hasLoja = true;
            if (!nmLoja.equalsIgnoreCase(cnab.getNmLoja())) {
                if (!nmLoja.isEmpty()) {
                    loja.put("saldo_conta", saldo);
                    loja.put("transacoes", transactions);
                    lojas.add(loja);
                    transactions = new ArrayList<>();
                }

                nmLoja = cnab.getNmLoja();
                loja = new HashMap<>();
                loja.put("nome_loja", nmLoja);
                loja.put("cpf", cnab.getCpf());
                loja.put("nome_dono", cnab.getNmDono());
                saldo = new Float(0);
            }
            transaction = new HashMap<>();
            transaction.put("tipo", this.getDescriptionTransaction(cnab.getTipo()));
            transaction.put("operacao", this.getDescriptionTransaction(cnab.getTipo()));
            if (entradas.contains(cnab.getTipo())) {
                transaction.put("valor", cnab.getValor());
                transaction.put("data", cnab.getDtTransacao());
                transactions.add(transaction);
                saldo += cnab.getValor();
            } else {
                transaction.put("valor", -cnab.getValor());
                transaction.put("data", cnab.getDtTransacao());
                transactions.add(transaction);
                saldo -= cnab.getValor();
            }
        }
        if (hasLoja) {
            loja.put("saldo_conta", saldo);
            loja.put("transacoes", transactions);
            lojas.add(loja);
        }

        return lojas;
    }

    private String getDescriptionTransaction(final Integer tipo) {
        String description;
        switch (tipo) {
            case 1 : description = "Débito";
                break;
            case 2 : description = "Boleto";
                break;
            case 3 : description = "Financiamento";
                break;
            case 4 : description = "Crédito";
                break;
            case 5 : description = "Recebimento Empréstimo";
                break;
            case 6 : description = "Vendas";
                break;
            case 7 : description = "Recebimento TED";
                break;
            case 8 : description = "Recebimento DOC";
                break;
            default: description = "Aluguel";
        }
        return description;
    }
}
