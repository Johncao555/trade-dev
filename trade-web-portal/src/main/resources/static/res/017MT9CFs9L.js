'use strict';mix_d("P13nPgShopNavUiCards__asin-to-categories-recommendations-card:asin-to-categories-recommendations-card__Ktli1e0c",["exports","tslib","@c/aui-carousel","@c/remote-operations","@c/scoped-dom"],function(d,e,b,f,g){function c(a){return a&&"object"===typeof a&&"default"in a?a:{"default":a}}var h=c(b);b=c(f);var k=c(g),l=b["default"].setup();d._operationNames=["getCarouselItems"];d.card=function(){return e.__awaiter(void 0,void 0,void 0,function(){var a,b;return e.__generator(this,function(c){switch(c.label){case 0:return a=
k["default"].cardRoot.getElementsByClassName("a-carousel-container")[0],b=h["default"].getCarousel(a),[4,b.initialized];case 1:return c.sent(),b.attachRemoteOperation(l.getCarouselItems),[2]}})})}});