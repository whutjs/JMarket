/**
 * Created by Mattxue on 2015/12/25.
 */
/**
 * @license AngularJS v1.2.15
 * (c) 2010-2014 Google, Inc. http://angularjs.org
 * License: MIT
 */
(function(window, angular, undefined) {
  'use strict';

  /**
   * @ngdoc module
   * @name ngTouch
   * @description
   *
   * # ngTouch
   *
   * The `ngTouch` module provides touch events and other helpers for touch-enabled devices.
   * The implementation is based on jQuery Mobile touch event handling
   * ([jquerymobile.com](http://jquerymobile.com/)).
   *
   *
   * See {
@link ngTouch.$swipe `$swipe`
} for usage.
   *
   * <div doc-module-components="ngTouch"></div>
   *
   */

// define ngTouch module
  /* global -ngTouch */
  var ngTouch = angular.module('ngTouch', []);

  /* global ngTouch: false */

  /**
   * @ngdoc service
   * @name $swipe
   *
   * @description
   * The `$swipe` service is a service that abstracts the messier details of hold-and-drag swipe
   * behavior, to make implementing swipe-related directives more convenient.
   *
   * Requires the {
@link ngTouch `ngTouch`
} module to be installed.
   *
   * `$swipe` is used by the `ngSwipeLeft` and `ngSwipeRight` directives in `ngTouch`, and by
   * `ngCarousel` in a separate component.
   *
   * # Usage
   * The `$swipe` service is an object with a single method: `bind`. `bind` takes an element
   * which is to be watched for swipes, and an object with four handler functions. See the
   * documentation for `bind` below.
   */

  ngTouch.factory('$swipe', [function() {

    // The total distance in any direction before we make the call on swipe vs. scroll.
    var MOVE_BUFFER_RADIUS = 10;

    function getCoordinates(event) {

      var touches = event.touches && event.touches.length ? event.touches : [event];
      var e = (event.changedTouches && event.changedTouches[0]) ||
        (event.originalEvent && event.originalEvent.changedTouches &&
        event.originalEvent.changedTouches[0]) ||
        touches[0].originalEvent || touches[0];

      return {

        x: e.clientX,
        y: e.clientY

      };

    }

    return {

      /**
       * @ngdoc method
       * @name $swipe#bind
       *
       * @description
       * The main method of `$swipe`. It takes an element to be watched for swipe motions, and an
       * object containing event handlers.
       *
       * The four events are `start`, `move`, `end`, and `cancel`. `start`, `move`, and `end`
       * receive as a parameter a coordinates object of the form `{
 x: 150, y: 310
}`.
       *
       * `start` is called on either `mousedown` or `touchstart`. After this event, `$swipe` is
       * watching for `touchmove` or `mousemove` events. These events are ignored until the total
       * distance moved in either dimension exceeds a small threshold.
       *
       * Once this threshold is exceeded, either the horizontal or vertical delta is greater.
       * - If the horizontal distance is greater, this is a swipe and `move` and `end` events follow.
       * - If the vertical distance is greater, this is a scroll, and we let the browser take over.
       *   A `cancel` event is sent.
       *
       * `move` is called on `mousemove` and `touchmove` after the above logic has determined that
       * a swipe is in progress.
       *
       * `end` is called when a swipe is successfully completed with a `touchend` or `mouseup`.
       *
       * `cancel` is called either on a `touchcancel` from the browser, or when we begin scrolling
       * as described above.
       *
       */
      bind: function(element, eventHandlers) {

        // Absolute total movement, used to control swipe vs. scroll.
        var totalX, totalY;
        // Coordinates of the start position.
        var startCoords;
        // Last event's position.
        var lastPos;
        // Whether a swipe is active.
        var active = false;

        element.on('touchstart mousedown', function(event) {

          startCoords = getCoordinates(event);
          active = true;
          totalX = 0;
          totalY = 0;
          lastPos = startCoords;
          eventHandlers['start'] && eventHandlers['start'](startCoords, event);

        });

        element.on('touchcancel', function(event) {

          active = false;
          eventHandlers['cancel'] && eventHandlers['cancel'](event);

        });

        element.on('touchmove mousemove', function(event) {

          if (!active) return;

          // Android will send a touchcancel if it thinks we're starting to scroll.
          // So when the total distance (+ or - or both) exceeds 10px in either direction,
          // we either:
          // - On totalX > totalY, we send preventDefault() and treat this as a swipe.
          // - On totalY > totalX, we let the browser handle it as a scroll.

          if (!startCoords) return;
          var coords = getCoordinates(event);

          totalX += Math.abs(coords.x - lastPos.x);
          totalY += Math.abs(coords.y - lastPos.y);

          lastPos = coords;

          if (totalX < MOVE_BUFFER_RADIUS && totalY < MOVE_BUFFER_RADIUS) {

            return;

          }

          // One of totalX or totalY has exceeded the buffer, so decide on swipe vs. scroll.
          if (totalY > totalX) {

            // Allow native scrolling to take over.
            active = false;
            eventHandlers['cancel'] && eventHandlers['cancel'](event);
            return;

          } else {

            // Prevent the browser from scrolling.
            event.preventDefault();
            eventHandlers['move'] && eventHandlers['move'](coords, event);

          }

        });

        element.on('touchend mouseup', function(event) {

          if (!active) return;
          active = false;
          eventHandlers['end'] && eventHandlers['end'](getCoordinates(event), event);

        });

      }

    };

  }]);

  /* global ngTouch: false */

  /**
   * @ngdoc directive
   * @name ngClick
   *
   * @description
   * A more powerful replacement for the default ngClick designed to be used on touchscreen
   * devices. Most mobile browsers wait about 300ms after a tap-and-release before sending
   * the click event. This version handles them immediately, and then prevents the
   * following click event from propagating.
   *
   * Requires the {
@link ngTouch `ngTouch`
} module to be installed.
   *
   * This directive can fall back to using an ordinary click event, and so works on desktop
   * browsers as well as mobile.
   *
   * This directive also sets the CSS class `ng-click-active` while the element is being held
   * down (by a mouse click or touch) so you can restyle the depressed element if you wish.
   *
   * @element ANY
   * @param {
expression
} ngClick {
@link guide/expression Expression
} to evaluate
   * upon tap. (Event object is available as `$event`)
   *
   */

  ngTouch.config(['$provide', function($provide) {

    $provide.decorator('ngClickDirective', ['$delegate', function($delegate) {

      // drop the default ngClick directive
      $delegate.shift();
      return $delegate;

    }]);

  }]);

  ngTouch.directive('ngClick', ['$parse', '$timeout', '$rootElement',
    function($parse, $timeout, $rootElement) {

      var TAP_DURATION = 750; // Shorter than 750ms is a tap, longer is a taphold or drag.
      var MOVE_TOLERANCE = 12; // 12px seems to work in most mobile browsers.
      var PREVENT_DURATION = 2500; // 2.5 seconds maximum from preventGhostClick call to click
      var CLICKBUSTER_THRESHOLD = 25; // 25 pixels in any dimension is the limit for busting clicks.

      var ACTIVE_CLASS_NAME = 'ng-click-active';
      var lastPreventedTime;
      var touchCoordinates;
      var lastLabelClickCoordinates;


      // TAP EVENTS AND GHOST CLICKS
      //
      // Why tap events?
      // Mobile browsers detect a tap, then wait a moment (usually ~300ms) to see if you're
      // double-tapping, and then fire a click event.
      //
      // This delay sucks and makes mobile apps feel unresponsive.
      // So we detect touchstart, touchmove, touchcancel and touchend ourselves and determine when
      // the user has tapped on something.
      //
      // What happens when the browser then generates a click event?
      // The browser, of course, also detects the tap and fires a click after a delay. This results in
      // tapping/clicking twice. So we do "clickbusting" to prevent it.
      //
      // How does it work?
      // We attach global touchstart and click handlers, that run during the capture (early) phase.
      // So the sequence for a tap is:
      // - global touchstart: Sets an "allowable region" at the point touched.
      // - element's touchstart: Starts a touch
      // (- touchmove or touchcancel ends the touch, no click follows)
      // - element's touchend: Determines if the tap is valid (didn't move too far away, didn't hold
      //   too long) and fires the user's tap handler. The touchend also calls preventGhostClick().
      // - preventGhostClick() removes the allowable region the global touchstart created.
      // - The browser generates a click event.
      // - The global click handler catches the click, and checks whether it was in an allowable region.
      //     - If preventGhostClick was called, the region will have been removed, the click is busted.
      //     - If the region is still there, the click proceeds normally. Therefore clicks on links and
      //       other elements without ngTap on them work normally.
      //
      // This is an ugly, terrible hack!
      // Yeah, tell me about it. The alternatives are using the slow click events, or making our users
      // deal with the ghost clicks, so I consider this the least of evils. Fortunately Angular
      // encapsulates this ugly logic away from the user.
      //
      // Why not just put click handlers on the element?
      // We do that too, just to be sure. The problem is that the tap event might have caused the DOM
      // to change, so that the click fires in the same position but something else is there now. So
      // the handlers are global and care only about coordinates and not elements.

      // Checks if the coordinates are close enough to be within the region.
      function hit(x1, y1, x2, y2) {

        return Math.abs(x1 - x2) < CLICKBUSTER_THRESHOLD && Math.abs(y1 - y2) < CLICKBUSTER_THRESHOLD;

      }

      // Checks a list of allowable regions against a click location.
      // Returns true if the click should be allowed.
      // Splices out the allowable region from the list after it has been used.
      function checkAllowableRegions(touchCoordinates, x, y) {

        for (var i = 0; i < touchCoordinates.length; i += 2) {

          if (hit(touchCoordinates[i], touchCoordinates[i + 1], x, y)) {

            touchCoordinates.splice(i, i + 2);
            return true; // allowable region

          }

        }
        return false; // No allowable region; bust it.

      }
    }]);
})






//
