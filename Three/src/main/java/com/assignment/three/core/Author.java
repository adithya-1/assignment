package com.assignment.three.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="author")
@NamedQueries({
        @NamedQuery(name = "com.assignment.three.core.Author.findAllAuthor",
                query = "SELECT A " +
                        "FROM Author AS A "
        )
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Author implements Serializable {
    @Id
    @Column(name="authorId")
    @JsonProperty
    private  String authorId;
    @Column(name="authorName")
    @JsonProperty
    private  String authorName;
}
