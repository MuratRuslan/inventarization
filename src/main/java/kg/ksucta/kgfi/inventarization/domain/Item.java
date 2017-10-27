package kg.ksucta.kgfi.inventarization.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by samsung on 12.05.2017.
 */
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date purchaseDate;
    private Date registrationDate;
    private String description;
    @Column(nullable = false)
    private String name = "";
    @Column(nullable = false, unique = true)
    private String articleNumber;
    private BigDecimal cost = new BigDecimal(0);
    @Column()
    private BigDecimal costSom = new BigDecimal(0);
    @ManyToOne
    @JoinColumn(name = "place")
    private Place place;
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;
    private String isbn = "";
    private String author = "";
    private String secondArtikelNumber = "";

    @ManyToOne
    @JoinColumn(name = "project")
    private Project project;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public BigDecimal getCost() {
        if (null == cost) return new BigDecimal(0);
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getIsbn() {
        if(isbn == null) return "";
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getCostSom() {
        if(costSom == null) return new BigDecimal(0);
        return costSom;
    }

    public void setCostSom(BigDecimal costSom) {
        this.costSom = costSom;
    }

    public String getAuthor() {
        if(author == null) return "";
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getSecondArtikelNumber() {
        if(secondArtikelNumber == null) return "";
        return secondArtikelNumber;
    }



    public void setSecondArtikelNumber(String secondArtikelNumber) {
        this.secondArtikelNumber = secondArtikelNumber;
    }
}
