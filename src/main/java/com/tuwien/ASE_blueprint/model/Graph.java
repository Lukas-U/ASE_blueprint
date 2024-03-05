package com.tuwien.ASE_blueprint.model;


import jakarta.persistence.*;


@Entity(name = "graph")
public class Graph {

    @Id
    @SequenceGenerator(name="yourSequenceGenerator", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator="yourSequenceGenerator")
    private Long id;

    @Column(name = "nr_of_vertices", length = 50, nullable = false, unique = false)
    private int nrOfVertices;

    @Enumerated(EnumType.STRING)
    private GraphType graphType;

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

    public GraphType getGraphType() {
        return graphType;
    }

    public void setGraphType(GraphType graphType) {
        this.graphType = graphType;
    }
}
