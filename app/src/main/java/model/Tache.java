package model;


public class Tache {

    private String tache;
    private Long id;

    public Tache() {
    }

    public Tache(String tache, Long id) {

        this.tache = tache;
        this.id = id;
    }


    public String getTache() {
        return tache;
    }

    public Tache setTache(String tache) {
        this.tache = tache;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Tache setId(Long id) {
        this.id = id;
        return this;
    }
}
