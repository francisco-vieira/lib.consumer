package br.jlinformatica.com;

import br.jlinformatica.com.acbr.ACBrMail;
import br.jlinformatica.com.acbr.config.*;
import br.jlinformatica.com.acbr.enums.CodResposta;
import br.jlinformatica.com.acbr.enums.NivelLog;
import br.jlinformatica.com.acbr.enums.TipoResposta;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws Exception {
       Logger logger = Logger.getLogger(Main.class.getName());
        ACBrMail base = ACBrMail.getInstance();
        //54c02764b6ce4d4c997ad14dc393e386ffad6645
        base.addAddress("colp.vieira@hotmail.com", "Francisco Vieira");
        base.addBody("Corpo da mensagem de testo");
        base.setSubject("Assunto do e-mail");

        try {
            base.send();
            logger.log(Level.INFO,"E-mail enviado com sucesso!");
        } catch (Exception e) {
            logger.log(Level.SEVERE,e.getMessage());
        }

    }
}