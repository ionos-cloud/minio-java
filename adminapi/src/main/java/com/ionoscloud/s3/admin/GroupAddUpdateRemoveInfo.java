

package com.ionoscloud.s3.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Represents groupAddUpdateRemove information. */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupAddUpdateRemoveInfo {
  @JsonProperty("group")
  private String group;

  @JsonProperty("groupStatus")
  private Status groupStatus;

  @JsonProperty("members")
  private List<String> members;

  @JsonProperty("isRemove")
  private boolean isRemove;

  public GroupAddUpdateRemoveInfo(
      @Nonnull @JsonProperty("group") String group,
      @Nullable @JsonProperty("groupStatus") Status groupStatus,
      @Nullable @JsonProperty("members") List<String> members,
      @Nullable @JsonProperty("isRemove") boolean isRemove) {
    this.group = Objects.requireNonNull(group, "Group must be provided");
    this.groupStatus = groupStatus;
    this.members = (members != null) ? Collections.unmodifiableList(members) : null;
    this.isRemove = isRemove;
  }

  public String group() {
    return group;
  }

  public Status groupStatus() {
    return groupStatus;
  }

  public List<String> members() {
    return Collections.unmodifiableList(members == null ? new LinkedList<>() : members);
  }

  public boolean isRemove() {
    return isRemove;
  }
}
