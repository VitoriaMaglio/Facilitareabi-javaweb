package br.com.facilitareabi.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
/**
 * Utilitário para criptografia e verificação de senhas usando BCrypt.
 */
public class PasswordHash {
    /**
     * Criptografa uma senha usando BCrypt.
     *
     * @param senha Senha em texto puro
     * @return Senha criptografada
     */
    public static String hashPassword(String senha){
        String salt = BCrypt.gensalt(10);
        //  System.out.println(salt);
        String senhaCriptografada = BCrypt.hashpw(senha, salt);
        return senhaCriptografada;
    }

    /**
     * Verifica se a senha fornecida corresponde ao hash armazenado.
     *
     * @param senha Senha em texto puro
     * @param hashPassword Hash da senha armazenada
     * @return true se corresponder, false caso contrário
     */
    public static boolean verificarSenha(String senha, String hashPassword){
        return BCrypt.checkpw(senha, hashPassword);
    }

}
