//package old.model;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import lombok.extern.jackson.Jacksonized;
//import org.hibernate.annotations.ColumnDefault;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//import org.hibernate.annotations.SQLDelete;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.io.Serializable;
//import java.time.OffsetDateTime;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.UUID;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
////@Entity
////@Table(name = "users")
//@ToString
//@Jacksonized
////@SQLDelete(sql = "update User set deleted=true where id=?")
//public class User implements UserDetails , Serializable {
//    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(unique = true)
//    private String email;
//    @JsonIgnore
//    private String password;
//    @JsonIgnore
//    private Boolean locked;
//    @JsonIgnore
//    private Boolean enabled;
//    @JsonIgnore
//    @Column(columnDefinition = "boolean default false", nullable = false)
//    private Boolean deleted;
//
//    @Enumerated(EnumType.STRING)
//    private Role role = Role.USER;
//    @JsonIgnore
//    public Integer code;
//    @JsonIgnore
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Token> tokens = new ArrayList<>();
//
//    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
//    @JoinColumn
//    private Person person;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Employee> employee= new ArrayList<>();
//
//    @JsonBackReference
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "userId")
//    private List<Clinic> clinics = new ArrayList<>();
//
//    @Size(max = 255)
//    @NotNull
//    @ToString.Exclude
//    @Column(name = "username", nullable = false)
//    private String username;
//
//    @OnDelete(action = OnDeleteAction.SET_NULL)
//    @ToString.Exclude
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "created_by")
//    private User createdBy;
//
//    @OnDelete(action = OnDeleteAction.SET_NULL)
//    @ToString.Exclude
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "updated_by")
//    private User updatedBy;
//
//    @OnDelete(action = OnDeleteAction.SET_NULL)
//    @ToString.Exclude
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "deleted_by")
//    private User deletedBy;
//
//    @ToString.Exclude
//    @Column(name = "external_id")
//    private UUID externalId;
//
//    @NotNull
//    @ColumnDefault("now()")
//    @ToString.Exclude
//    @Column(name = "created_at", nullable = false)
//    private OffsetDateTime createdAt;
//
//    @NotNull
//    @ColumnDefault("now()")
//    @ToString.Exclude
//    @Column(name = "updated_at", nullable = false)
//    private OffsetDateTime updatedAt;
//
//    @ToString.Exclude
//    @Column(name = "deleted_at")
//    private OffsetDateTime deletedAt;
//
//    @JsonIgnore
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }
//
//    @JsonIgnore
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @JsonIgnore
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @JsonIgnore
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @JsonIgnore
//    @Override
//    public boolean isAccountNonLocked() {
//        return locked;
//    }
//
//    @JsonIgnore
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @JsonIgnore
//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }
//}
