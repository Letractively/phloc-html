/**
 * Copyright (C) 2006-2014 phloc systems
 * http://www.phloc.com
 * office[at]phloc[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.phloc.html.hc.html;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.ValueEnforcer;
import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.html.CHTMLAttributeValues;
import com.phloc.html.CHTMLAttributes;
import com.phloc.html.hc.IHCHasChildrenMutable;
import com.phloc.html.hc.IHCNodeWithChildren;
import com.phloc.html.hc.api.EHCInputType;
import com.phloc.html.hc.conversion.IHCConversionSettingsToNode;
import com.phloc.html.hc.impl.AbstractHCInput;
import com.phloc.html.request.IHCRequestFieldBoolean;

/**
 * Represents an HTML &lt;input&gt; element with type "checkbox"
 *
 * @author Philip Helger, Boris Gregorcic
 */
public class HCCheckBox extends AbstractHCInput <HCCheckBox>
{
  /** Check-box is not checked by default */
  public static final boolean DEFAULT_CHECKED = false;

  /** Emit a hidden field that indicates that the check-box was in the request. */
  public static final boolean DEFAULT_EMIT_HIDDEN_FIELD = true;

  /**
   * The prefix to appended to the field name of the checkbox to create the
   * hidden field.
   */
  public static final String DEFAULT_HIDDEN_FIELD_PREFIX = "__";

  private String m_sValue;
  private boolean m_bChecked = DEFAULT_CHECKED;
  private boolean m_bEmitHiddenField = DEFAULT_EMIT_HIDDEN_FIELD;

  /**
   * Constructor
   */
  public HCCheckBox ()
  {
    super (EHCInputType.CHECKBOX);
  }

  /**
   * Constructor
   *
   * @param sName
   *        The name of this check-box (used as field name)
   */
  public HCCheckBox (@Nullable final String sName)
  {
    this ();
    setName (sName);
  }

  /**
   * Constructor
   *
   * @param sName
   *        The name of this check-box (used as field name)
   * @param bChecked
   *        Whether or not it should be initially checked
   */
  public HCCheckBox (@Nullable final String sName, final boolean bChecked)
  {
    this (sName);
    setChecked (bChecked);
  }

  /**
   * Constructor
   *
   * @param sName
   *        The name of this check-box (used as field name)
   * @param bChecked
   *        Whether or not it should be initially checked
   * @param sValue
   *        The value to be set for this check-box
   */
  public HCCheckBox (@Nullable final String sName, final boolean bChecked, @Nullable final String sValue)
  {
    this (sName, bChecked);
    setValue (sValue);
  }

  /**
   * Constructor, where the passed value decides upon the initial check state
   *
   * @param aRF
   *        The request field
   * @param sValue
   *        The value to be set for this check-box
   */
  public HCCheckBox (@Nonnull final IHCRequestFieldBoolean aRF, @Nullable final String sValue)
  {
    this (aRF.getFieldName (), aRF.isChecked (sValue), sValue);
  }

  /**
   * Constructor
   *
   * @param aRF
   *        The request field
   */
  public HCCheckBox (@Nonnull final IHCRequestFieldBoolean aRF)
  {
    this (aRF, null);
  }

  /**
   * @return The field value, maybe <code>null</code>
   */
  @Nullable
  public final String getValue ()
  {
    return m_sValue;
  }

  /**
   * Sets the passed field value
   *
   * @param sValue
   *        Value to use.
   * @return This object for chaining
   */
  @Nonnull
  public final HCCheckBox setValue (@Nullable final String sValue)
  {
    m_sValue = sValue;
    return this;
  }

  /**
   * @return Whether or not the check-box is currently checked
   */
  public final boolean isChecked ()
  {
    return m_bChecked;
  }

  /**
   * Set the checked state according to the passed value
   *
   * @param bChecked
   *        new checked state
   * @return This object for chaining
   */
  @Nonnull
  public final HCCheckBox setChecked (final boolean bChecked)
  {
    m_bChecked = bChecked;
    return this;
  }

  /**
   * @return Whether or not hidden fields will be emitted
   */
  public final boolean isEmitHiddenField ()
  {
    return m_bEmitHiddenField;
  }

  /**
   * Sets whether or not hidden fields will be emitted according to the passed
   * value
   *
   * @param bEmitHiddenField
   *        <code>true</code> to emit the hidden field, <code>false</code> to
   *        avoid.
   * @return This object for chaining
   */
  @Nonnull
  public final HCCheckBox setEmitHiddenField (final boolean bEmitHiddenField)
  {
    m_bEmitHiddenField = bEmitHiddenField;
    return this;
  }

  /**
   * Get the hidden field name for this checkbox.
   *
   * @return <code>null</code> if no field name ({@link #getName()}) is present
   *         or a non-<code>null</code> and non-empty string.
   * @see #getHiddenFieldName(String)
   */
  @Nullable
  public final String getHiddenFieldName ()
  {
    final String sFieldName = getName ();
    if (StringHelper.hasNoText (sFieldName))
      return null;
    return getHiddenFieldName (sFieldName);
  }

  @Override
  public void onAdded (@Nonnegative final int nIndex, @Nonnull final IHCHasChildrenMutable <?, ?> aParent)
  {
    if (m_bEmitHiddenField)
    {
      final String sHiddenFieldName = getHiddenFieldName ();
      if (sHiddenFieldName != null)
        ((IHCNodeWithChildren <?>) aParent).addChild (new HCHiddenField (sHiddenFieldName, getValue ()));
    }
  }

  @Override
  public void onRemoved (@Nonnegative final int nIndex, @Nonnull final IHCHasChildrenMutable <?, ?> aParent)
  {
    if (m_bEmitHiddenField && getHiddenFieldName () != null)
      ((IHCNodeWithChildren <?>) aParent).removeChild (nIndex);
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (m_sValue != null)
      aElement.setAttribute (CHTMLAttributes.VALUE, m_sValue);
    if (m_bChecked)
      aElement.setAttribute (CHTMLAttributes.CHECKED, CHTMLAttributeValues.CHECKED);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("value", m_sValue)
                            .append ("checked", m_bChecked)
                            .append ("emitHiddenField", m_bEmitHiddenField)
                            .toString ();
  }

  /**
   * Get the name of the automatic hidden field associated with a check-box.
   *
   * @param sFieldName
   *        The name of the check-box.
   * @return The name of the hidden field associated with the given check-box
   *         name.
   */
  @Nonnull
  @Nonempty
  public static String getHiddenFieldName (@Nonnull @Nonempty final String sFieldName)
  {
    ValueEnforcer.notEmpty (sFieldName, "FieldName");
    return DEFAULT_HIDDEN_FIELD_PREFIX + sFieldName;
  }
}
