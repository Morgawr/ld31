(ns game.core
  (:require [clojure.browser.repl :as repl]
            [goog.dom :as dom]
            [goog.events :as events]
            [goog.events.EventType :as event-type]
            novelette.screens.loadingscreen
            novelette.screens.storyscreen
            [novelette.input :as input]
            [novelette.screen :as screen]
            [game.script :as script]))

(def image-list {:background "img/background.png"
                 :psyke "img/psyke.png"
                 :irc "img/irc.png"
                 :dialogue-ui "img/dialogbg.png"
                 :cursor "img/cursor.png"
                 :choicebg "img/choicebg.png"
                 :elisa-happy "img/elisa_happy-scaled.png"
                 :elisa-angry "img/elisa_angry-scaled.png"
                 :elisa-blush "img/elisa_blush-scaled.png"
                 :kurt-happy "img/kurt_happy-scaled.png"
                 :kurt-angry "img/kurt_angry-scaled.png"
                 :kurt-fire "img/kurt_fired-scaled.png"
                 :angelblue-normal "img/angelblue_normal-scaled.png"
                 :angelblue-angry "img/angelblue_angry-scaled.png"
                 :angelblue-crazy "img/angelblue_crazy-scaled.png"
                 })

(def audio-list {
                 :day2-sad "sound/day2-sad_trimmed"
                 :day3-nice "sound/day3-nice_trimmed"
                 :day3-bad "sound/day3_trimmed"
                 :main-theme "sound/main-theme_trimmed"
                 })

(def start-game
  (into novelette.screens.storyscreen/BASE-STATE
        {:scrollfront (:body script/start)}))

;(defn ^:export connect
;  []
;  (.log js/console "Starting local connection...")
;  (repl/connect "http://localhost:9000/repl")
;  (.log js/console "...connected"))

(defn ^:export init []
  (let [document (dom/getDocument)
        canvas (dom/getElement "surface")
        ctx (.getContext canvas "2d")
        loading (novelette.screens.loadingscreen/init ctx canvas image-list audio-list
                                                      (novelette.screens.storyscreen/init  ctx  canvas
                                                       start-game))
        state (screen/State. [loading] 0 ctx canvas)]
    (events/listen js/window event-type/MOUSEMOVE input/mouse-move-listener)
    (events/listen js/window event-type/CLICK input/mouse-click-listener)
    (screen/main-game-loop state)))
