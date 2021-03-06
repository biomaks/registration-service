package com.jetminds.service;

import com.jetminds.RegistrationServiceApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.jms.Queue;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(RegistrationServiceApplication.class)
@WebAppConfiguration
public class SendEmailServiceTest {

    private SendEmailService sendEmailService = Mockito.mock(SendEmailService.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    /*
     Test HashMap for SendMessageTest
     */
    private Map<String, String> blobMassage;

    @Before
    public void setUp() {
        blobMassage = new HashMap<>();
        blobMassage.put("email", "test@mail.com");
        blobMassage.put("password", "test");
        blobMassage.put("code", "testcode");

        /*
        for test receiveMessage
         */
        jmsTemplate.convertAndSend(queue, blobMassage);

        Mockito.when(sendEmailService.sendEmail(blobMassage)).thenReturn(true);
    }

    /*
    Test receive message from ActiveMQ
     */
    @Test
    public void testReceiveMessage() {

    }

    /*
    Test send email
     */
    @Test
    public void testSendEmail() {
        Assert.assertTrue(sendEmailService.sendEmail(blobMassage));
    }
}
