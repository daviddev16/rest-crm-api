package com.daviddev16.parecer;

import java.util.UUID;

public interface ParecerService {

    Parecer registrarParecerEmAtendimento(UUID atendimentoId, Parecer parecer);

}
