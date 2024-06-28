package com.elanart.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Choice {
    private String id;
    private String content;
    private boolean is_correct;
    private String question_id;
}
