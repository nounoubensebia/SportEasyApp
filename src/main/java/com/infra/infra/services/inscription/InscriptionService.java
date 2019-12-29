package com.infra.infra.services.inscription;

import com.infra.infra.models.Inscription;

import java.util.List;

public interface InscriptionService {

    public List<Inscription> getAll();
    public Inscription getById(long id);
    public void create(Inscription inscription);
    public void update(Inscription inscription);
    public void delete(Inscription inscription);
}
