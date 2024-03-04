package com.tuwien.ASE_blueprint.model;


import jakarta.persistence.*;


@Entity(name = "graph")
public class Graph {

    @Id
    @SequenceGenerator(name="yourSequenceGenerator", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator="yourSequenceGenerator")
    private Long id;

    @Column(name = "nr_of_vertices", nullable = false)
    private int nrOfVertices;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getNrOfVertices() {
        return nrOfVertices;
    }

    public void setNrOfVertices(int nrOfVertices) {
        this.nrOfVertices = nrOfVertices;
    }
}
