package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Member{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username; //객체는 username사용하고 db에서는 name이라고 사용

    @Embedded
    private Period workperiod;

    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns =
    @JoinColumn(name = "MEMBER_ID")
    )
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();


    /* @ElementCollection
     @CollectionTable(name = "ADDRESS", joinColumns =
         @JoinColumn(name = "MEMBER_ID")
     )
     private List<Address> addresHistory = new ArrayList<>();*/
    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addresHistory = new ArrayList<>();


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",
                    column=@Column(name = "WORK_CITY")),
            @AttributeOverride(name="street",
                    column=@Column(name = "WORK_STREET")),
            @AttributeOverride(name="zipcode",
                    column=@Column(name = "WORK_ZIPCODE"))
    })
    private Address workAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Period getWorkperiod() {
        return workperiod;
    }

    public void setWorkperiod(Period workperiod) {
        this.workperiod = workperiod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<AddressEntity> getAddresHistory() {
        return addresHistory;
    }

    public void setAddresHistory(List<AddressEntity> addresHistory) {
        this.addresHistory = addresHistory;
    }
}
