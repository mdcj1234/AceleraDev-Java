package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {

        if(texto == null)
            throw new NullPointerException();

        if(texto.length() == 0)
            throw new IllegalArgumentException();

        texto = texto.toLowerCase();
        StringBuilder textocriptografado = new StringBuilder();

        for(int i = 0; i < texto.length(); i++){

            int ascii = texto.charAt(i);

            if(ascii >= 97 && ascii <= 122) {
                ascii += 3;
                if(ascii > 122) {
                    ascii -= 26;
                }
            }

            textocriptografado.append((char) ascii);

        }

        return textocriptografado.toString();
    }

    @Override
    public String descriptografar(String texto) {

        if(texto == null)
            throw new NullPointerException();

        if(texto.length() == 0)
            throw new IllegalArgumentException();

        texto = texto.toLowerCase();
        StringBuilder textodescriptografado = new StringBuilder();

        for(int i = 0; i < texto.length(); i++){

            int ascii = texto.charAt(i);

            if(ascii >= 97 && ascii <= 122) {
                ascii -= 3;
                if(ascii < 97) {
                    ascii += 26;
                }
            }

            textodescriptografado.append((char) ascii);

        }

        return textodescriptografado.toString();
    }
}
