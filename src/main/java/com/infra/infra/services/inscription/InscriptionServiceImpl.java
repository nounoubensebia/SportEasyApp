package com.infra.infra.services.inscription;

import com.infra.infra.models.Inscription;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscriptionServiceImpl implements InscriptionService {

    private InscriptionRepository inscriptionRepository;

    @Autowired
    public void setInscriptionRepository(InscriptionRepository inscriptionRepository)
    {
        this.inscriptionRepository = inscriptionRepository;
    }

    @Override
    public List<Inscription> getAll() {
        return IterableUtils.toList(inscriptionRepository.findAll());
    }

    @Override
    public Inscription getById(long id) {
        if (inscriptionRepository.findById(id).isPresent())
            return inscriptionRepository.findById(id).get();
        else
            return null;
    }

    @Override
    public void create(Inscription inscription) {
        inscriptionRepository.save(inscription);
    }

    @Override
    public void update(Inscription inscription) {
        inscriptionRepository.save(inscription);
    }

    @Override
    public void delete(Inscription inscription) {
        inscriptionRepository.delete(inscription);
    }

}
