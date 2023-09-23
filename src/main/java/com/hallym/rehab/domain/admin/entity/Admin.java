package com.hallym.rehab.domain.admin.entity;

import com.hallym.rehab.domain.reservation.entity.Time;
import com.hallym.rehab.domain.user.entity.MemberRole;
import com.hallym.rehab.domain.video.entity.Video;
import com.hallym.rehab.global.baseEntity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Admin extends BaseTimeEntity {

    @Id
    @Column(nullable = false, unique = true)
    private String mid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    private int age;

    private String sex;

    private String phone;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>(); //권한 정보

    @Builder.Default
    @OneToMany(mappedBy = "vno", cascade = CascadeType.ALL)
    private Set<Video> videoList = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "tno", cascade = CascadeType.ALL)
    private Set<Time> timeList = new HashSet<>();

    public void addTime(Time time) {
        this.timeList.add(time);
    }

    @ColumnDefault("false")
    private boolean is_deleted;

    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void addUser(String mid, String password, String name, String phone, Set<MemberRole> roleSet) {
        this.mid = mid;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.roleSet = roleSet;
    }
}
