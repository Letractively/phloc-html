package com.phloc.html.hc.html;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.text.IPredefinedLocaleTextProvider;
import com.phloc.html.EHTMLElement;
import com.phloc.html.hc.IHCNode;
import com.phloc.html.hc.IHCNodeBuilder;
import com.phloc.html.hc.impl.AbstractHCElementWithChildren;

/**
 * Represents an HTML &lt;SAMP&gt; element<br>
 * This file is automatically generated from com.phloc.html.supplementary.main.MainCreateHCClasses so DO NOT EDIT!
 * 
 * @author Philip Helger
 */
public class HCSamp extends AbstractHCElementWithChildren <HCSamp>
{
  /**
   * Create a new SAMP element
   */
  public HCSamp ()
  {
    super (EHTMLElement.SAMP);
  }

  /**
   * Create a new SAMP element with the passed child text
   * @param aChild The child text provider to be appended. May be <code>null</code>
   * @return The created HCSamp element and never <code>null</code>
   */
  @Nonnull
  public static HCSamp create (@Nullable final IPredefinedLocaleTextProvider aChild)
  {
    return new HCSamp ().addChild (aChild);
  }

  /**
   * Create a new SAMP element with the passed child texts
   * @param aChildren The child texts to be appended. May be <code>null</code>
   * @return The created HCSamp element and never <code>null</code>
   */
  @Nonnull
  public static HCSamp create (@Nullable final IPredefinedLocaleTextProvider... aChildren)
  {
    return new HCSamp ().addChildren (aChildren);
  }

  /**
   * Create a new SAMP element with the passed child text
   * @param sChild The child to be appended. May be <code>null</code>
   * @return The created HCSamp element and never <code>null</code>
   */
  @Nonnull
  public static HCSamp create (@Nullable final String sChild)
  {
    return new HCSamp ().addChild (sChild);
  }

  /**
   * Create a new SAMP element with the passed child texts
   * @param aChildren The child texts to be appended. May be <code>null</code>
   * @return The created HCSamp element and never <code>null</code>
   */
  @Nonnull
  public static HCSamp create (@Nullable final String... aChildren)
  {
    return new HCSamp ().addChildren (aChildren);
  }

  /**
   * Create a new SAMP element with the passed child node
   * @param aChild The child node to be appended. May be <code>null</code>
   * @return The created HCSamp element and never <code>null</code>
   */
  @Nonnull
  public static HCSamp create (@Nullable final IHCNodeBuilder aChild)
  {
    return new HCSamp ().addChild (aChild);
  }

  /**
   * Create a new SAMP element with the passed child nodes
   * @param aChildren The child nodes to be appended. May be <code>null</code>
   * @return The created HCSamp element and never <code>null</code>
   */
  @Nonnull
  public static HCSamp create (@Nullable final IHCNodeBuilder... aChildren)
  {
    return new HCSamp ().addChildren (aChildren);
  }

  /**
   * Create a new SAMP element with the passed child node
   * @param aChild The child node to be appended. May be <code>null</code>
   * @return The created HCSamp element and never <code>null</code>
   */
  @Nonnull
  public static HCSamp create (@Nullable final IHCNode aChild)
  {
    return new HCSamp ().addChild (aChild);
  }

  /**
   * Create a new SAMP element with the passed child nodes
   * @param aChildren The child nodes to be appended. May be <code>null</code>
   * @return The created HCSamp element and never <code>null</code>
   */
  @Nonnull
  public static HCSamp create (@Nullable final IHCNode... aChildren)
  {
    return new HCSamp ().addChildren (aChildren);
  }

  /**
   * Create a new SAMP element with the passed child nodes
   * @param aChildren The child nodes to be appended. May be <code>null</code>
   * @return The created HCSamp element and never <code>null</code>
   */
  @Nonnull
  public static HCSamp create (@Nullable final Iterable <? extends IHCNode> aChildren)
  {
    return new HCSamp ().addChildren (aChildren);
  }
}
