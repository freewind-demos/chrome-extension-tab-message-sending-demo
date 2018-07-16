if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'chrome-ts2kt'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'chrome-ts2kt'.");
}
this['chrome-ts2kt'] = function (_, Kotlin) {
  'use strict';
  var defineInlineFunction = Kotlin.defineInlineFunction;
  var wrapFunction = Kotlin.wrapFunction;
  var JsObj = defineInlineFunction('chrome-ts2kt.JsObj_287e2$', wrapFunction(function () {
    var Any = Object;
    var throwCCE = Kotlin.throwCCE;
    return function () {
      var tmp$;
      return (tmp$ = {}) == null || Kotlin.isType(tmp$, Any) ? tmp$ : throwCCE();
    };
  }));
  _.JsObj_287e2$ = JsObj;
  Kotlin.defineModule('chrome-ts2kt', _);
  return _;
}(typeof this['chrome-ts2kt'] === 'undefined' ? {} : this['chrome-ts2kt'], kotlin);
