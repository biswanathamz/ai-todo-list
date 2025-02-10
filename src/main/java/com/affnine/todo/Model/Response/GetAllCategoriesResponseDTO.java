package com.affnine.todo.Model.Response;

import lombok.Data;

@Data
public class GetAllCategoriesResponseDTO {
    public long id;
    public String name;
    public String description;
}
