package org.example.lab;

import org.example.lab.app.service.preferences.impl.SettingsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.example.lab.app.domain.preferences.Settings;
import org.example.lab.app.domain.preferences.SettingsType;
import org.example.lab.app.repository.SettingsTypeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SettingsServiceTest {

    @Mock
    private SettingsTypeRepository settingsTypeRepository;

    @InjectMocks
    private SettingsServiceImpl settingsService;

    private SettingsType settingsType;
    private Map<String, Settings> configuracoes;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializando a entidade Settings e configurando diferentes tipos de valores
        Settings stringSetting = new Settings();
        stringSetting.setValue("Configuração de string");

        Settings longSetting = new Settings();
        longSetting.setValue(100L);

        Settings byteSetting = new Settings();
        byteSetting.setValue(new byte[]{1, 2, 3});

        // Map de configurações
        configuracoes = new HashMap<>();
        configuracoes.put("stringKey", stringSetting);
        configuracoes.put("longKey", longSetting);
        configuracoes.put("byteKey", byteSetting);

        // Inicializando o SettingsType com o Map de configurações
        settingsType = new SettingsType();
        settingsType.setConfiguracoes(configuracoes);
    }

    @Test
    void testGetConfigValueByKey_String() {
        // Mockando o retorno do repositório
        when(settingsTypeRepository.findById(1L)).thenReturn(Optional.of(settingsType));

        // Testando o valor String
        Optional<Object> value = settingsService.getConfigValueByKey(1L, "stringKey");
        assertEquals("Configuração de string", value.orElse(null));
    }

    @Test
    void testGetConfigValueByKey_Long() {
        // Mockando o retorno do repositório
        when(settingsTypeRepository.findById(1L)).thenReturn(Optional.of(settingsType));

        // Testando o valor Long
        Optional<Object> value = settingsService.getConfigValueByKey(1L, "longKey");
        assertEquals(100L, value.orElse(null));
    }

//    @Test
//    void testGetConfigValueByKey_ByteArray() {
//        // Mockando o retorno do repositório
//        when(settingsTypeRepository.findById(1L)).thenReturn(Optional.of(settingsType));
//
//        // Testando o valor byte[]
//        Optional<Object> value = settingsService.getConfigValueByKey(1L, "byteKey");
//        assertEquals(new byte[]{1, 2, 3}, value.orElse(null));
//    }

    @Test
    void testGetConfigValueByKey_NotFound() {
        // Mockando um retorno vazio do repositório
        when(settingsTypeRepository.findById(1L)).thenReturn(Optional.empty());

        // Testando o caso de configuração não encontrada
        Optional<Object> value = settingsService.getConfigValueByKey(1L, "nonExistentKey");
        assertEquals(Optional.empty(), value);
    }
}