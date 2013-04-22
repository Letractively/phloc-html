/**
 * Copyright (C) 2006-2013 phloc systems
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
package com.phloc.html.hc.customize;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phloc.commons.annotations.OverrideOnDemand;
import com.phloc.commons.idfactory.GlobalIDFactory;
import com.phloc.commons.string.StringParser;
import com.phloc.css.ECSSUnit;
import com.phloc.css.property.CCSSProperties;
import com.phloc.html.EHTMLVersion;
import com.phloc.html.css.DefaultCSSClassProvider;
import com.phloc.html.css.ICSSClassProvider;
import com.phloc.html.hc.CHCParam;
import com.phloc.html.hc.IHCControl;
import com.phloc.html.hc.IHCElement;
import com.phloc.html.hc.IHCNode;
import com.phloc.html.hc.IHCNodeWithChildren;
import com.phloc.html.hc.api.IHCCanBeDisabled;
import com.phloc.html.hc.html.AbstractHCCell;
import com.phloc.html.hc.html.AbstractHCTable;
import com.phloc.html.hc.html.HCBody;
import com.phloc.html.hc.html.HCButton;
import com.phloc.html.hc.html.HCButton_Submit;
import com.phloc.html.hc.html.HCCheckBox;
import com.phloc.html.hc.html.HCCol;
import com.phloc.html.hc.html.HCColGroup;
import com.phloc.html.hc.html.HCEdit;
import com.phloc.html.hc.html.HCEditFile;
import com.phloc.html.hc.html.HCEditPassword;
import com.phloc.html.hc.html.HCForm;
import com.phloc.html.hc.html.HCHead;
import com.phloc.html.hc.html.HCHiddenField;
import com.phloc.html.hc.html.HCLink;
import com.phloc.html.hc.html.HCRadioButton;
import com.phloc.html.hc.html.HCRow;
import com.phloc.html.hc.html.HCScript;
import com.phloc.html.hc.impl.HCEntityNode;
import com.phloc.html.hc.utils.HCSpecialNodeHandler;
import com.phloc.html.js.EJSEvent;
import com.phloc.html.js.builder.JSExpr;
import com.phloc.html.js.builder.JSInvocation;
import com.phloc.html.js.builder.jquery.JQuery;

// ESCA-JAVA0116:
/**
 * The default implementation of {@link IHCCustomizer} performing some default
 * class assignments etc.
 * 
 * @author Philip Helger
 */
@Immutable
public class HCDefaultCustomizer extends HCEmptyCustomizer
{
  public static final ICSSClassProvider CSS_CLASS_BUTTON = DefaultCSSClassProvider.create ("button");
  public static final ICSSClassProvider CSS_CLASS_CHECKBOX = DefaultCSSClassProvider.create ("checkbox");
  public static final ICSSClassProvider CSS_CLASS_EDIT = DefaultCSSClassProvider.create ("edit");
  public static final ICSSClassProvider CSS_CLASS_EDIT_FILE = DefaultCSSClassProvider.create ("edit_file");
  public static final ICSSClassProvider CSS_CLASS_EDIT_PASSWORD = DefaultCSSClassProvider.create ("edit_password");
  public static final ICSSClassProvider CSS_CLASS_HIDDEN = DefaultCSSClassProvider.create ("hidden");
  public static final ICSSClassProvider CSS_CLASS_RADIO = DefaultCSSClassProvider.create ("radio");

  // For controls only
  public static final ICSSClassProvider CSS_CLASS_DISABLED = DefaultCSSClassProvider.create ("disabled");
  public static final ICSSClassProvider CSS_CLASS_READONLY = DefaultCSSClassProvider.create ("readonly");

  // For buttons
  public static final ICSSClassProvider CSS_CLASS_INVISIBLE_BUTTON = DefaultCSSClassProvider.create ("pdaf_invisible_button");

  // For tables
  public static final ICSSClassProvider CSS_FORCE_COLSPAN = DefaultCSSClassProvider.create ("force_colspan");

  // JS Code
  public static final JSInvocation JS_BLUR = JSExpr.invoke ("blur");

  private static final Logger s_aLogger = LoggerFactory.getLogger (HCDefaultCustomizer.class);

  public HCDefaultCustomizer ()
  {}

  @Nonnull
  protected HCButton createFakeSubmitButton ()
  {
    return new HCButton_Submit ("").addClass (CSS_CLASS_INVISIBLE_BUTTON);
  }

  @Override
  public void customizeNode (@Nonnull final IHCNodeWithChildren <?> aParentElement,
                             @Nonnull final IHCNode aNode,
                             @Nonnull final EHTMLVersion eHTMLVersion)
  {
    if (aNode instanceof IHCElement <?>)
    {
      final IHCElement <?> aElement = (IHCElement <?>) aNode;
      if (aElement instanceof HCButton)
      {
        aElement.addClass (CSS_CLASS_BUTTON);
      }
      else
        if (aElement instanceof HCCheckBox)
        {
          final HCCheckBox aCheckBox = (HCCheckBox) aElement;
          aCheckBox.addClass (CSS_CLASS_CHECKBOX);

          // If no value is present, assign the default value
          if (aCheckBox.getValue () == null)
            aCheckBox.setValue (CHCParam.VALUE_CHECKED);
        }
        else
          if (aElement instanceof HCEdit)
          {
            aElement.addClass (CSS_CLASS_EDIT);
          }
          else
            if (aElement instanceof HCEditFile)
            {
              aElement.addClasses (CSS_CLASS_EDIT, CSS_CLASS_EDIT_FILE);
            }
            else
              if (aElement instanceof HCEditPassword)
              {
                aElement.addClasses (CSS_CLASS_EDIT, CSS_CLASS_EDIT_PASSWORD);
              }
              else
                if (aElement instanceof HCForm)
                {
                  final HCForm aForm = (HCForm) aElement;
                  if (aForm.isSubmitPressingEnter ())
                  {
                    final HCButton aButton = createFakeSubmitButton ();
                    aButton.setTabIndex (aForm.getSubmitButtonTabIndex ());
                    aForm.addChild (aButton);
                  }
                }
                else
                  if (aElement instanceof HCHiddenField)
                  {
                    aElement.addClass (CSS_CLASS_HIDDEN);
                  }
                  else
                    if (aElement instanceof HCRadioButton)
                    {
                      aElement.addClass (CSS_CLASS_RADIO);
                    }
                    else
                      if (aElement instanceof AbstractHCTable <?>)
                      {
                        final AbstractHCTable <?> aTable = (AbstractHCTable <?>) aElement;
                        final HCColGroup aColGroup = aTable.getColGroup ();
                        // bug fix for IE9 table layout bug
                        // (http://msdn.microsoft.com/en-us/library/ms531161%28v=vs.85%29.aspx)
                        // IE9 only interprets column widths if the first row
                        // does
                        // not use colspan (i.e. at least one row does not use
                        // colspan)
                        if (aColGroup != null &&
                            aColGroup.hasColumns () &&
                            aTable.hasBodyRows () &&
                            aTable.getFirstBodyRow ().isColspanUsed ())
                        {
                          // Create a dummy row with explicit widths
                          final HCRow aRow = new HCRow (false).addClass (CSS_FORCE_COLSPAN);
                          for (final HCCol aCol : aColGroup.getAllColumns ())
                          {
                            final AbstractHCCell aCell = aRow.addAndReturnCell (HCEntityNode.newNBSP ());
                            final int nWidth = StringParser.parseInt (aCol.getWidth (), -1);
                            if (nWidth >= 0)
                              aCell.addStyle (CCSSProperties.WIDTH.newValue (ECSSUnit.px (nWidth)));
                          }
                          aTable.addBodyRow (0, aRow);
                        }
                      }

      // Unfocusable?
      if (aElement.isUnfocusable ())
        aElement.setEventHandler (EJSEvent.ONFOCUS, JS_BLUR);

      // Disable
      if (aElement instanceof IHCCanBeDisabled <?>)
        if (((IHCCanBeDisabled <?>) aElement).isDisabled ())
          aElement.addClass (CSS_CLASS_DISABLED);

      if (aElement instanceof IHCControl <?>)
      {
        // Specific control stuff
        final IHCControl <?> aCtrl = (IHCControl <?>) aElement;

        // Read only?
        if (aCtrl.isReadonly ())
          aCtrl.addClass (CSS_CLASS_READONLY);

        if (aCtrl.isFocused ())
        {
          // for focusing we need an ID!
          if (aCtrl.getID () == null)
            aCtrl.setID (GlobalIDFactory.getNewStringID ());

          // Add this out of band node
          // Note: assuming jQuery
          aParentElement.addChild (new HCScript (JQuery.idRef (aCtrl.getID ()).focus ()));
        }
      }
    }
  }

  /**
   * Check if the passed out-of-band node belongs to the body or to the head.
   * 
   * @param aOOBNode
   *        The node to check. Never <code>null</code>.
   * @return <code>true</code> if it belongs to the body, <code>false</code> if
   *         it belongs to the head.
   */
  @OverrideOnDemand
  protected boolean isOutOfBandBodyNode (@Nonnull final IHCNode aOOBNode)
  {
    // JS nodes go to body
    if (HCSpecialNodeHandler.isJSNode (aOOBNode))
      return true;

    return false;
  }

  @Override
  public void handleOutOfBandNodes (@Nonnull final List <IHCNode> aOutOfBandNodes,
                                    @Nonnull final HCHead aHead,
                                    @Nonnull final HCBody aBody)
  {
    if (aOutOfBandNodes == null)
      throw new NullPointerException ("OutOfBandNodes");
    if (aHead == null)
      throw new NullPointerException ("Head");
    if (aBody == null)
      throw new NullPointerException ("Body");

    // Add all existing JS and CSS nodes from the head, as they are known to be
    // out-of-band
    final List <IHCNode> aCompleteOOBList = new ArrayList <IHCNode> ();
    aCompleteOOBList.addAll (aHead.getAllAndRemoveAllJSNodes ());
    aCompleteOOBList.addAll (aHead.getAllAndRemoveAllCSSNodes ());
    aCompleteOOBList.addAll (aOutOfBandNodes);

    // First merge all JS and CSS nodes (and keep document.ready() as it is)
    final List <IHCNode> aMergedOOBNodes = HCSpecialNodeHandler.getMergedInlineCSSAndJSNodes (aCompleteOOBList, true);

    // And now move either to head or body
    for (final IHCNode aNode : aMergedOOBNodes)
    {
      if (isOutOfBandBodyNode (aNode))
      {
        // It's a body node
        aBody.addChild (aNode);
      }
      else
      {
        // It's a head node
        if (HCSpecialNodeHandler.isCSSNode (aNode))
          aHead.addCSS (aNode);
        else
          if (HCSpecialNodeHandler.isJSNode (aNode))
            aHead.addJS (aNode);
          else
            if (aNode instanceof HCLink)
            {
              // Manually add all non-stylesheet LINK elements
              aHead.addLink ((HCLink) aNode);
            }
            else
              s_aLogger.error ("Found illegal out-of-band head node: " + aNode);
      }
    }
  }
}
