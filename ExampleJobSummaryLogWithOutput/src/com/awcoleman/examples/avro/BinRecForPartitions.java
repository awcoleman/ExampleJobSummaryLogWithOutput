/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.awcoleman.examples.avro;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class BinRecForPartitions extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"BinRecForPartitions\",\"namespace\":\"com.awcoleman.examples.avro\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"recid\",\"type\":\"long\"},{\"name\":\"eventType\",\"type\":\"int\"},{\"name\":\"eventDurationSec\",\"type\":\"int\"},{\"name\":\"endDatetime\",\"type\":\"string\"},{\"name\":\"endDate\",\"type\":\"string\"},{\"name\":\"endHour\",\"type\":\"int\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence name;
  @Deprecated public long recid;
  @Deprecated public int eventType;
  @Deprecated public int eventDurationSec;
  @Deprecated public java.lang.CharSequence endDatetime;
  @Deprecated public java.lang.CharSequence endDate;
  @Deprecated public int endHour;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public BinRecForPartitions() {}

  /**
   * All-args constructor.
   */
  public BinRecForPartitions(java.lang.CharSequence name, java.lang.Long recid, java.lang.Integer eventType, java.lang.Integer eventDurationSec, java.lang.CharSequence endDatetime, java.lang.CharSequence endDate, java.lang.Integer endHour) {
    this.name = name;
    this.recid = recid;
    this.eventType = eventType;
    this.eventDurationSec = eventDurationSec;
    this.endDatetime = endDatetime;
    this.endDate = endDate;
    this.endHour = endHour;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return name;
    case 1: return recid;
    case 2: return eventType;
    case 3: return eventDurationSec;
    case 4: return endDatetime;
    case 5: return endDate;
    case 6: return endHour;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: name = (java.lang.CharSequence)value$; break;
    case 1: recid = (java.lang.Long)value$; break;
    case 2: eventType = (java.lang.Integer)value$; break;
    case 3: eventDurationSec = (java.lang.Integer)value$; break;
    case 4: endDatetime = (java.lang.CharSequence)value$; break;
    case 5: endDate = (java.lang.CharSequence)value$; break;
    case 6: endHour = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'name' field.
   */
  public java.lang.CharSequence getName() {
    return name;
  }

  /**
   * Sets the value of the 'name' field.
   * @param value the value to set.
   */
  public void setName(java.lang.CharSequence value) {
    this.name = value;
  }

  /**
   * Gets the value of the 'recid' field.
   */
  public java.lang.Long getRecid() {
    return recid;
  }

  /**
   * Sets the value of the 'recid' field.
   * @param value the value to set.
   */
  public void setRecid(java.lang.Long value) {
    this.recid = value;
  }

  /**
   * Gets the value of the 'eventType' field.
   */
  public java.lang.Integer getEventType() {
    return eventType;
  }

  /**
   * Sets the value of the 'eventType' field.
   * @param value the value to set.
   */
  public void setEventType(java.lang.Integer value) {
    this.eventType = value;
  }

  /**
   * Gets the value of the 'eventDurationSec' field.
   */
  public java.lang.Integer getEventDurationSec() {
    return eventDurationSec;
  }

  /**
   * Sets the value of the 'eventDurationSec' field.
   * @param value the value to set.
   */
  public void setEventDurationSec(java.lang.Integer value) {
    this.eventDurationSec = value;
  }

  /**
   * Gets the value of the 'endDatetime' field.
   */
  public java.lang.CharSequence getEndDatetime() {
    return endDatetime;
  }

  /**
   * Sets the value of the 'endDatetime' field.
   * @param value the value to set.
   */
  public void setEndDatetime(java.lang.CharSequence value) {
    this.endDatetime = value;
  }

  /**
   * Gets the value of the 'endDate' field.
   */
  public java.lang.CharSequence getEndDate() {
    return endDate;
  }

  /**
   * Sets the value of the 'endDate' field.
   * @param value the value to set.
   */
  public void setEndDate(java.lang.CharSequence value) {
    this.endDate = value;
  }

  /**
   * Gets the value of the 'endHour' field.
   */
  public java.lang.Integer getEndHour() {
    return endHour;
  }

  /**
   * Sets the value of the 'endHour' field.
   * @param value the value to set.
   */
  public void setEndHour(java.lang.Integer value) {
    this.endHour = value;
  }

  /** Creates a new BinRecForPartitions RecordBuilder */
  public static com.awcoleman.examples.avro.BinRecForPartitions.Builder newBuilder() {
    return new com.awcoleman.examples.avro.BinRecForPartitions.Builder();
  }
  
  /** Creates a new BinRecForPartitions RecordBuilder by copying an existing Builder */
  public static com.awcoleman.examples.avro.BinRecForPartitions.Builder newBuilder(com.awcoleman.examples.avro.BinRecForPartitions.Builder other) {
    return new com.awcoleman.examples.avro.BinRecForPartitions.Builder(other);
  }
  
  /** Creates a new BinRecForPartitions RecordBuilder by copying an existing BinRecForPartitions instance */
  public static com.awcoleman.examples.avro.BinRecForPartitions.Builder newBuilder(com.awcoleman.examples.avro.BinRecForPartitions other) {
    return new com.awcoleman.examples.avro.BinRecForPartitions.Builder(other);
  }
  
  /**
   * RecordBuilder for BinRecForPartitions instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<BinRecForPartitions>
    implements org.apache.avro.data.RecordBuilder<BinRecForPartitions> {

    private java.lang.CharSequence name;
    private long recid;
    private int eventType;
    private int eventDurationSec;
    private java.lang.CharSequence endDatetime;
    private java.lang.CharSequence endDate;
    private int endHour;

    /** Creates a new Builder */
    private Builder() {
      super(com.awcoleman.examples.avro.BinRecForPartitions.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(com.awcoleman.examples.avro.BinRecForPartitions.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.recid)) {
        this.recid = data().deepCopy(fields()[1].schema(), other.recid);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.eventType)) {
        this.eventType = data().deepCopy(fields()[2].schema(), other.eventType);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.eventDurationSec)) {
        this.eventDurationSec = data().deepCopy(fields()[3].schema(), other.eventDurationSec);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.endDatetime)) {
        this.endDatetime = data().deepCopy(fields()[4].schema(), other.endDatetime);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.endDate)) {
        this.endDate = data().deepCopy(fields()[5].schema(), other.endDate);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.endHour)) {
        this.endHour = data().deepCopy(fields()[6].schema(), other.endHour);
        fieldSetFlags()[6] = true;
      }
    }
    
    /** Creates a Builder by copying an existing BinRecForPartitions instance */
    private Builder(com.awcoleman.examples.avro.BinRecForPartitions other) {
            super(com.awcoleman.examples.avro.BinRecForPartitions.SCHEMA$);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.recid)) {
        this.recid = data().deepCopy(fields()[1].schema(), other.recid);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.eventType)) {
        this.eventType = data().deepCopy(fields()[2].schema(), other.eventType);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.eventDurationSec)) {
        this.eventDurationSec = data().deepCopy(fields()[3].schema(), other.eventDurationSec);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.endDatetime)) {
        this.endDatetime = data().deepCopy(fields()[4].schema(), other.endDatetime);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.endDate)) {
        this.endDate = data().deepCopy(fields()[5].schema(), other.endDate);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.endHour)) {
        this.endHour = data().deepCopy(fields()[6].schema(), other.endHour);
        fieldSetFlags()[6] = true;
      }
    }

    /**
      * Gets the value of the 'name' field.
      */
    public java.lang.CharSequence getName() {
      return name;
    }

    /**
      * Sets the value of the 'name' field.
      * @param value the value to set.
      */
    public com.awcoleman.examples.avro.BinRecForPartitions.Builder setName(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.name = value;
      fieldSetFlags()[0] = true;
      return this; 
    }

    /**
      * Checks whether the 'name' field has been set.
      */
    public boolean hasName() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'name' field.
      */
    public com.awcoleman.examples.avro.BinRecForPartitions.Builder clearName() {
      name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'recid' field.
      */
    public java.lang.Long getRecid() {
      return recid;
    }

    /**
      * Sets the value of the 'recid' field.
      * @param value the value to set.
      */
    public com.awcoleman.examples.avro.BinRecForPartitions.Builder setRecid(long value) {
      validate(fields()[1], value);
      this.recid = value;
      fieldSetFlags()[1] = true;
      return this; 
    }

    /**
      * Checks whether the 'recid' field has been set.
      */
    public boolean hasRecid() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'recid' field.
      */
    public com.awcoleman.examples.avro.BinRecForPartitions.Builder clearRecid() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'eventType' field.
      */
    public java.lang.Integer getEventType() {
      return eventType;
    }

    /**
      * Sets the value of the 'eventType' field.
      * @param value the value to set.
      */
    public com.awcoleman.examples.avro.BinRecForPartitions.Builder setEventType(int value) {
      validate(fields()[2], value);
      this.eventType = value;
      fieldSetFlags()[2] = true;
      return this; 
    }

    /**
      * Checks whether the 'eventType' field has been set.
      */
    public boolean hasEventType() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'eventType' field.
      */
    public com.awcoleman.examples.avro.BinRecForPartitions.Builder clearEventType() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'eventDurationSec' field.
      */
    public java.lang.Integer getEventDurationSec() {
      return eventDurationSec;
    }

    /**
      * Sets the value of the 'eventDurationSec' field.
      * @param value the value to set.
      */
    public com.awcoleman.examples.avro.BinRecForPartitions.Builder setEventDurationSec(int value) {
      validate(fields()[3], value);
      this.eventDurationSec = value;
      fieldSetFlags()[3] = true;
      return this; 
    }

    /**
      * Checks whether the 'eventDurationSec' field has been set.
      */
    public boolean hasEventDurationSec() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'eventDurationSec' field.
      */
    public com.awcoleman.examples.avro.BinRecForPartitions.Builder clearEventDurationSec() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'endDatetime' field.
      */
    public java.lang.CharSequence getEndDatetime() {
      return endDatetime;
    }

    /**
      * Sets the value of the 'endDatetime' field.
      * @param value the value to set.
      */
    public com.awcoleman.examples.avro.BinRecForPartitions.Builder setEndDatetime(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.endDatetime = value;
      fieldSetFlags()[4] = true;
      return this; 
    }

    /**
      * Checks whether the 'endDatetime' field has been set.
      */
    public boolean hasEndDatetime() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'endDatetime' field.
      */
    public com.awcoleman.examples.avro.BinRecForPartitions.Builder clearEndDatetime() {
      endDatetime = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'endDate' field.
      */
    public java.lang.CharSequence getEndDate() {
      return endDate;
    }

    /**
      * Sets the value of the 'endDate' field.
      * @param value the value to set.
      */
    public com.awcoleman.examples.avro.BinRecForPartitions.Builder setEndDate(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.endDate = value;
      fieldSetFlags()[5] = true;
      return this; 
    }

    /**
      * Checks whether the 'endDate' field has been set.
      */
    public boolean hasEndDate() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'endDate' field.
      */
    public com.awcoleman.examples.avro.BinRecForPartitions.Builder clearEndDate() {
      endDate = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'endHour' field.
      */
    public java.lang.Integer getEndHour() {
      return endHour;
    }

    /**
      * Sets the value of the 'endHour' field.
      * @param value the value to set.
      */
    public com.awcoleman.examples.avro.BinRecForPartitions.Builder setEndHour(int value) {
      validate(fields()[6], value);
      this.endHour = value;
      fieldSetFlags()[6] = true;
      return this; 
    }

    /**
      * Checks whether the 'endHour' field has been set.
      */
    public boolean hasEndHour() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'endHour' field.
      */
    public com.awcoleman.examples.avro.BinRecForPartitions.Builder clearEndHour() {
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    public BinRecForPartitions build() {
      try {
        BinRecForPartitions record = new BinRecForPartitions();
        record.name = fieldSetFlags()[0] ? this.name : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.recid = fieldSetFlags()[1] ? this.recid : (java.lang.Long) defaultValue(fields()[1]);
        record.eventType = fieldSetFlags()[2] ? this.eventType : (java.lang.Integer) defaultValue(fields()[2]);
        record.eventDurationSec = fieldSetFlags()[3] ? this.eventDurationSec : (java.lang.Integer) defaultValue(fields()[3]);
        record.endDatetime = fieldSetFlags()[4] ? this.endDatetime : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.endDate = fieldSetFlags()[5] ? this.endDate : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.endHour = fieldSetFlags()[6] ? this.endHour : (java.lang.Integer) defaultValue(fields()[6]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}