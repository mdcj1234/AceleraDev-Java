package challenge;

import java.util.*;

public class Estacionamento {

    int vagas = 10;
    Map<Integer,Carro> carros = new HashMap<>();
    ArrayList<Carro> filacarros = new ArrayList<>();

    private boolean validarEntradaCarro(Carro carro){

        if(carro.getMotorista() == null)
            throw new EstacionamentoException("Carro sem motorista.");
        else if(carro.getMotorista().getIdade() < 18)
            throw new EstacionamentoException("Motorista menor de idade.");
        else if(carro.getMotorista().getPontos() > 20)
            throw new EstacionamentoException("Motorista com carteira suspensa.");

        return true;
    }

    private int validarSaidaMotorista() {

        Optional<Carro> carro = filacarros.stream()
                .filter(value -> value.getMotorista().getIdade() < 55)
                .findFirst();

        return carro.map(value -> filacarros.indexOf(value)).orElse(-1);
    }

    public void estacionar(Carro carro) {

        if(validarEntradaCarro(carro)){

            if(carros.size() < vagas){
                carros.put(carro.hashCode(),carro);
                filacarros.add(carro);
            } else {
                try{
                    int index = validarSaidaMotorista();
                    carros.remove(filacarros.get(index).hashCode());
                    filacarros.remove(index);

                    carros.put(carro.hashCode(),carro);
                    filacarros.add(carro);
                } catch (IndexOutOfBoundsException e) {
                    throw new EstacionamentoException("Estacionamento lotado.");
                }
            }
        }
    }

    public int carrosEstacionados() {
        return filacarros.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return (carros.containsKey(carro.hashCode()));
    }

}
