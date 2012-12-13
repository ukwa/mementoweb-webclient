package models;

import play.data.validation.Constraints.*;

public class Query {

    @Required
    public String url;
    
    public String archive;

}
