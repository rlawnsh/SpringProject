package hello.toy_project.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Dislikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private StockNews stockNews;

    public Dislikes(StockNews stockNews, User byUsername) {
        this.stockNews = stockNews;
        this.user = byUsername;
    }
}
