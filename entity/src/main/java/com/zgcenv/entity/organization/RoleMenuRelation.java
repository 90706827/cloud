package com.zgcenv.entity.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName RoleMenuRelation
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-17
 * @Version 1.0
 **/
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role_menu_relation")
public class RoleMenuRelation implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "idUtils")
    @GenericGenerator(name = "idUtils", strategy = "com.zgcenv.entity.utils.IdUtils")
    private Long id;

    @Basic
    @Column(name = "menu_id")
    private Long menuId;

    @Basic
    @Column(name = "role_id")
    private Long roleId;

}
