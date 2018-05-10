/*
 * DRACOON
 * Version 4.4.0 - built at: 2017-12-04 04:14:43, API server: https://demo.dracoon.com/api/v4
 *
 * OpenAPI spec version: 4.4.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package ch.cyberduck.core.sds.io.swagger.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * LogOperationList
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-05-03T10:55:56.129+02:00")
public class LogOperationList {
  @JsonProperty("logOperations")
  private String logOperations = null;

  public LogOperationList logOperations(String logOperations) {
    this.logOperations = logOperations;
    return this;
  }

   /**
   * List of all Log Operations
   * @return logOperations
  **/
  @ApiModelProperty(required = true, value = "List of all Log Operations")
  public String getLogOperations() {
    return logOperations;
  }

  public void setLogOperations(String logOperations) {
    this.logOperations = logOperations;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LogOperationList logOperationList = (LogOperationList) o;
    return Objects.equals(this.logOperations, logOperationList.logOperations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(logOperations);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LogOperationList {\n");
    
    sb.append("    logOperations: ").append(toIndentedString(logOperations)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}
