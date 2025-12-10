//package old.model;
//
//import old.config.Auditable;
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.OneToMany;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.SuperBuilder;
//import lombok.extern.jackson.Jacksonized;
//import org.hibernate.annotations.SQLDelete;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@SuperBuilder
//@NoArgsConstructor
//@AllArgsConstructor
////@Entity
//@Jacksonized
////@SQLDelete(sql = "update clinic set deleted=true where id=?")
//public class Clinic extends Auditable<Long> implements Serializable {
//    private String name;
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "clinicId")
//    private List<Department> departments = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "clinicId")
//    private List<Preiskurant> preiskurants = new ArrayList<>();
//
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "clinic_person",
//            joinColumns = {@JoinColumn(name = "clinic_id")},
//            inverseJoinColumns = {@JoinColumn(name = "person_id")}
//    )
//    private List<Person> persons = new ArrayList<>();
//    public void addPerson(Person person) {
//        this.persons.add(person);
//    }
//}
