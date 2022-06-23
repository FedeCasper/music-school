package com.emusic.school.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private double totalPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.EAGER)
    private Set<PurchaseOrder> purchaseOrder = new HashSet<>();

    public Ticket() {}

    public Ticket(double totalPrice, Client client) {
        this.totalPrice = totalPrice;
        this.client = client;

    }

    public long getId() {return id;}

    public Client getClient() {return client;}
    public void setClient(Client client) {this.client = client;}

    public Set<PurchaseOrder> getPurchaseOrder() {return purchaseOrder;}
    public void setPurchaseOrder(Set<PurchaseOrder> purchaseOrder) {this.purchaseOrder = purchaseOrder;}
}