(ns app.hello
  (:require [reagent.core :as r]
            [reagent-forms.core :refer [bind-fields]]))

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

(defn row [label input]
  [:div
   [:div [:label label]]
   [:div input]])

(defn login-form-template []
  [:div
    (row "Username"
      [:input {:field :text :id :name}])
    (row "Game Name"
      [:input {:field :text :id :game}])
    (row "Passkey"
      [:input {:field (if @host-clicked :text :password) :id :pass}])])

(defn login-form [] 
  [:div
    [:h2 (if @host-clicked "Host Game" "Join Game")]
    [bind-fields (login-form-template) user]
    [:p (str @user)]
    [:p (str @host-clicked)]
    [:button "Start"]])

(defn hello []
  [:<>
   [:h1 "Scalaria"]
   [:p "This is the temporary, ugly UI for testing the backend logic and connectivity."]
    (if 
      (or @host-clicked @login-clicked) 
      (login-form) 
      (login-buttons))])


