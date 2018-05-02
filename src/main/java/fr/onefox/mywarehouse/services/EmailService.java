package fr.onefox.mywarehouse.services;

import fr.onefox.mywarehouse.domain.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.MessageFormat;

@Slf4j
@Service
public class EmailService {

    private static final String CAN_NOT_SEND_EMAIL_FOR_THE_MOMENT = "Can not send email for the moment.";

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.from}")
    private String emailFrom;

    @Value("${spring.mail.to}")
    private String emailTo;

    /**
     * Send email for a transaction
     *
     * @param transaction
     * @param attachement
     * @throws Exception
     */
    public void sendTransactionEmail(Transaction transaction, File attachement) throws Exception {
        log.info(MessageFormat.format("Send email - ID {0} - {1} - To : {2}", transaction.get_id(), transaction.getType(), emailFrom));

        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = null;
            helper = new MimeMessageHelper(message, true);
            helper.setSubject("New transaction 📦");
            helper.setText(MessageFormat.format("New transaction type : {0}.\nID: {1}\n\nMore information in attachment.", transaction.getType(), transaction.get_id()));
            helper.setFrom(MessageFormat.format("My Warehouse 🚀 <{0}>", emailFrom));
            helper.setTo(emailFrom);
            helper.addAttachment(transaction.get_id() + "." + FilenameUtils.getExtension(attachement.getName()), new FileSystemResource(attachement.getAbsolutePath()));
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error(CAN_NOT_SEND_EMAIL_FOR_THE_MOMENT);
            throw new Exception(CAN_NOT_SEND_EMAIL_FOR_THE_MOMENT);
        }

        emailSender.send(message);
    }

}
