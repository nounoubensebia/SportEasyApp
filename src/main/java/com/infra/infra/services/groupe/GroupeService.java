package com.infra.infra.services.groupe;

import com.infra.infra.models.Groupe;

import java.util.List;

public interface GroupeService {

    /**
     *
     * @return all groupes
     */
    public List<Groupe> getAll();

    /**
     *
     * @param id
     * @return groupe, null if not exists
     */
    public Groupe getById(long id);
    public void create(Groupe groupe);
    public void update(Groupe groupe);
    public void delete(Groupe groupe);

}
