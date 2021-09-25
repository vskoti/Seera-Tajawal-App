package com.base.pagefactory ;

/**
 * @apiNote how a {@link MobileDriverPage} should load a page prior to instantiating fields
 */
public enum LoadBehavior {
  ASSUME_PAGE_LOADED,
  LOAD_PAGE_IF_BLANK,
  LOAD_PAGE,
  ;
}
