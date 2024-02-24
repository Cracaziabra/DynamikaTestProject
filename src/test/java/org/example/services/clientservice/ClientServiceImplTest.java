package org.example.services.clientservice;

import org.example.core.Client;
import org.example.exceptions.ClientNotFoundException;
import org.example.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientServiceImplTest {

    @MockBean
    private ClientRepository clientRepository;
    @Autowired
    private ClientServiceImpl clientService;
    private Client testClient = new Client();
    private String word = "word!";

    @BeforeEach
    public void before() {
        testClient.setFirstName(word);
        testClient.setLastName(word);
        testClient.setPatronymic(word);
    }

    @Test
    void updateNonExistingClient() {
        when(clientRepository.existsById(any())).thenReturn(false);
        assertThrows(ClientNotFoundException.class, () -> clientService.updateClient(testClient, 1L));
    }

    @Test
    void updateExistingClient() {
        when(clientRepository.existsById(any())).thenReturn(true);
        when(clientRepository.saveAndFlush(any())).thenReturn(testClient);
        assertThat(clientService.updateClient(testClient, 1L)).isEqualTo(testClient);
    }
}