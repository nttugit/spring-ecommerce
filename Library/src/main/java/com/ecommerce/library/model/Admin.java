package com.ecommerce.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    // Cascade All giúp tự động lưu hặc xoá các record của table tham chiếu đến table này.
    // Ví dụ: khi xoá admin thì xoá luôn record ở table admin_roles mà nó thamc chiếu đến admin này
    // Fetch Eager: khi truy vấn dữ liệu, nó load het các dữ liệu tham chiếu luôn
    // Còn lazy chỉ load mỗi bảng đó, khi cần mới load tiếp
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="admin_roles",
            joinColumns = @JoinColumn(name="admin_id", referencedColumnName = "admin_id"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "role_id")
    )
    private Collection<Role> roles;
}
