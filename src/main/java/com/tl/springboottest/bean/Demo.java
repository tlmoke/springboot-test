package com.tl.springboottest.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

// import org.hibernate.annotations.Entity;
// import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Table(name = "demo", schema = "ROOT")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Demo {
    @Id
    @GeneratedValue
    private long id;// 主键.
    @Column(name = "name")
    @NonNull
    private String name;// 测试名称
}
