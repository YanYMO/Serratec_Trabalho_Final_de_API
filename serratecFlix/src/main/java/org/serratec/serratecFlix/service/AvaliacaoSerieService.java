package org.serratec.serratecFlix.service;

import org.serratec.serratecFlix.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoSerieService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AvaliacaoSerieRepository avaliacaoSerieRepository;

    @Autowired
    private SerieRepository serieRepository;
}