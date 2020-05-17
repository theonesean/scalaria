(ns app.hello
  (:require [reagent.core :as r]
            [reagent-forms.core :refer [bind-fields]]))

(defn click-counter [click-count]
  [:div
   "The atom " [:code "click-count"] " has value: "
   @click-count ". "
   [:input {:type "button" :value "Click me!"
            :on-click #(swap! click-count inc)}]])

(def click-count (r/atom 0))

(def login-clicked (r/atom false))
(def host-clicked (r/atom false))

(def is-host (r/atom false))
(def user (r/atom {:name ""
                   :game ""
                   :pass ""}))

(defn login-buttons []
  [:div.my-2 
    [:button.py-2.px-4.rounded.bg-white.font-bold 
      {:on-click #(reset! login-clicked true)}
      "Join Game"]
    [:button.py-2.px-4.rounded.bg-white.font-bold 
      {:on-click #(reset! host-clicked true)}
      "Host Game"]])

(defn login-form [is-host] 
  [:div
    [:h2 (if is-host "Host Game" "Join Game")]
    [:input {:type "text" :value "Username"
             :on-change}]
    [:input {:type "text" :value "Game Name"}]
    [:input {:type (if is-host "text" "password") :value "Passkey"}]]
    [:button {:onclick }])

(defn hello []
  [:<>
   [:h1 "Scalaria"]
   [:p "This is the temporary, ugly UI for testing the backend logic and connectivity."]
   (cond
    @host-clicked  [:div "Host A Game"]
    @login-clicked [:div "Log In"]
    :else           (login-buttons))])
