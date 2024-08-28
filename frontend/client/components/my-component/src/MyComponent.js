/**
 * Component MyComponent is defined as
 * `<e-my-component>`
 *
 * Imperatively create component
 * @example
 * let component = new MyComponent();
 *
 * Declaratively create component
 * @example
 * <e-my-component></e-my-component>
 *
 * @extends {LitComponent}
 */
import { definition } from "@eui/component";
import { LitComponent, html } from "@eui/lit-component";
import style from "./myComponent.css";
import "@eui/table";
import "@eui/draggable";
import "@eui/theme";
import "@eui/layout";
/**
 * @property {Boolean} propOne - show active/inactive state.
 * @property {String} propTwo  - show active/inactive state.
 */
@definition("e-my-component", {
  style,
  home: "my-component",
  props: {
    buttondisabled: { type: "Boolean", default: true },
    createButton: { type: "Boolean", default: false },
    response: { attribute: false },
    propTwo: { type: "String", default: "False" },
    show: { type: "Boolean", default: false },
    teamShow: { type: "Boolean", default: false },
    memberShow: { type: "Boolean", default: false },
    mood: { type: "String", default: "" },
    commentMood: { type: "String", default: "" },
    MadData: { type: "String", default: "" },
    priority: {type: "Boolean", default: false},
    commentShow: { type: "Boolean", default: false },
  },
})
export default class MyComponent extends LitComponent {
  /**
   * Render the <e-my-component> component. This function is called each time a
   * prop changes.
   */
  handleEvent(event) {
    // handle the addition of an item to the drop area
    if (event.type === "add") {
      console.log(`Card Title: ${event.item.cardTitle}`);
      // Card Title: Card One

      console.log(`Title of destination drop area: ${event.to.dropAreaTitle}`);
      // Title of destination drop area: Drop Area Two

      console.log(
        `Title of originating drop area: ${event.from.dropAreaTitle}`
      );
      // Title of originating drop area: Drop Area One
    }
  }

  _onFlyoutButtonClicked2() {
    this.register(FlyoutPanel, "eui-flyout-panel");
    this.myFlyoutPanel = this.createElement("eui-flyout-panel");
    this.myFlyoutPanel.panelTitle = "This is the title";

    // append the flyout component...
    this.parent.appendChild(this.myFlyoutPanel);
  }

  _onFlyoutButtonClicked() {
    const flyoutPanel = document.createElement("eui-layout-v0-flyout-panel");
    flyoutPanel.panelTitle = "This is the title";

    // create the content
    const div = document.createElement("div");
    div.slot = "content";
    div.innerText =
      "This text appears inside the content area of the flyout panel. Anything can be added here.";

    // append the content to the flyout panel. It is automatically inserted into the content slot.
    flyoutPanel.appendChild(div);

    // append the flyout component...
    parent.appendChild(flyoutPanel);
  }

  _createButtonClicked(value) {
    // const parent=document.createElement('eui-draggable-v0-drop-area')
    const card = document.createElement("eui-layout-v0-card");
    card.cardTitle = "This is the title";
    card.drag = true;
    console.log(parent);

    // create the content
    const div = document.createElement("div");
    div.slot = "content";
    div.innerText =
      "This text appears inside the content area of the card. Anything can be added here.";

    // append the content to the card. It is automatically inserted into the content slot.
    card.appendChild(div);

    // append tooltip to the card
    const toolTip = document.createElement("eui-base-v0-tooltip");
    toolTip.slot = "action";
    toolTip.message = "Edit";
    toolTip.position = "left";
    card.appendChild(toolTip);

    // add the edit icon to the tooltip
    const editAction = document.createElement("eui-v0-icon");
    editAction.name = "edit";
    editAction.addEventListener("click", (event) => {
      event.stopPropagation();
      console.log("edit action clicked");
    });
    toolTip.appendChild(editAction);

    // append the card component...
    parent[0].appendChild(card);
  }

  _addItem() {
    const textField = this.shadowRoot.querySelector(".todo__text-field");
    fetch("http://localhost:9090/item", {
      method: "POST",
      body: JSON.stringify({
        author: "John",
        teamName: "Team B",
        description: "New item",
        mood: "Sad",
      }),
      headers: {
        "Content-Type": "application/json; charset=UTF-8",
      },
    })
      .then(function (response) {
        return response.json();
      })
      .then(function (data) {
        console.log(data);
      });
  }

  _createTeam() {
    const textField = this.shadowRoot.querySelector(".team__text-field");
    if (textField.value.length > 0) {
      fetch("http://localhost:9090/team", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name: textField.value,
        }),
      })
        .then(function (response) {
          return response.json();
        })
        .then(function (data) {
          console.log(data);
        });
    }
  }

  _createGladComment(){
    const textField1 = this.shadowRoot.querySelector(
     ".teamGlad__comment"
   );

   if (textField1.value.length > 0) {
     fetch("http://localhost:9090/item/comment", {
       method: "POST",
       headers: {
         "Content-Type": "application/json",
       },
       body: JSON.stringify({
         comment: textField1.value,
       }),
     })
       .then(function (response) {
         return response.json();
       })
       .then(function (data) {
         console.log(data);
       });
   }
 }

  _getTeams() {
    fetch("http://localhost:9090/team")
      .then((response) => {
        return response.json();
      })

      .then(
        function (data) {
          // var textAreaTeams = this.shadowRoot.getElementById("myData");
          const textAreaTeams = this.shadowRoot.querySelector("eui-table-v0");
          for (var i = 0; i < data.length; i++) {
            var div = document.createElement("div");
            div.innerHTML = "Team name: " + data[i].name;

            console.log(div);

            const rowToAdd = { col1: div };

            textAreaTeams.data = [...textAreaTeams.data, rowToAdd];

            // textAreaTeams.appendChild(div);
          }
          console.log(textAreaTeams);
          console.log("parsed json ", data);
        }.bind(this)
      )
      .catch(function (ex) {
        console.log("parsing failed", ex);
      });
  }

  _createTask() {
    const textField1 = this.shadowRoot.querySelector(
      ".item_author__text-field"
    );
    const textField2 = this.shadowRoot.querySelector(
      ".item_description__text-field"
    );
    const textField3 = this.shadowRoot.querySelector(".item_mood__text-field");
    const textField4 = this.shadowRoot.querySelector(
      ".item_teamname__text-field"
    );
    const textField5 = this.shadowRoot.querySelector(
      ".item_priority__text-field"
    );

    if (textField1.value.length > 0) {
      fetch("http://localhost:9090/item", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          author: textField1.value,
          description: textField2.value,
          mood: textField3.value,
          teamName: textField4.value,
          priority: textField5.value,
        }),
      })
        .then(function (response) {
          return response.json();
        })
        .then(function (data) {
          console.log(data);
        });
    }
  }

  _createComment(){
      const textField1 = this.shadowRoot.querySelector(
        ".comment_author__text-field"
      );
      const textField2 = this.shadowRoot.querySelector(
        ".comment_item__text-field"
      );
      const textField3 = this.shadowRoot.querySelector(".comment__text-field");
      const textField4 = this.shadowRoot.querySelector(
        ".comment_mood__text-field"
      );
  
      if (textField1.value.length > 0) {
        fetch("http://localhost:9090/item/comment", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            author: textField1.value,
            item: textField2.value,
            comment: textField3.value,
            mood: textField4.value,
          }),
        })
          .then(function (response) {
            return response.json();
          })
          .then(function (data) {
            console.log(data);
          });
      
    }
  }

  _deleteSprint(){
    fetch("http://localhost:9090/item"), {
      method: "DELETE",
    }
    fetch("http://localhost:9090/members"), {
      method: "DELETE",
    }
    fetch("http://localhost:9090/team"), {
      method: "DELETE",
    }
  }

  _createMember() {
    const textField1 = this.shadowRoot.querySelector(
      ".member_name__text-field"
    );
    const textField2 = this.shadowRoot.querySelector(
      ".member_email__text-field"
    );
    const textField3 = this.shadowRoot.querySelector(
      ".member_teamname__text-field"
    );

    if (textField1.value.length > 0) {
      fetch("http://localhost:9090/members", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name: textField1.value,
          email: textField2.value,
          teamName: textField3.value,
        }),
      })
        .then(function (response) {
          return response.json();
        })
        .then(function (data) {
          console.log(data);
        });
    }
  }
  _getSadMood() {
    fetch("http://localhost:9090/item/sad")
      .then(function (response) {
        return response.json();
      })
      .then(
        function (data) {
          // var textAreaTeams = this.shadowRoot.getElementById("myData");
          const textAreaTeams = this.shadowRoot.querySelector(".team__table");
          for (var i = 0; i < data.length; i++) {
            var div = document.createElement("div");
            div.innerHTML = "Author: " + data[i].author;
            div.innerHTML = "Description: " + data[i].description;
            div.innerHTML = "Mood: " + data[i].mood;
            div.innerHTML = "Team Name: " + data[i].teamName;
            div.innerHTML = "Priority: " + data[i].priority;

            console.log(div);

            const rowToAdd = {
              col1: data[i].author,
              col2: data[i].description,
              col3: data[i].mood,
              col4: data[i].teamName,
              col5: data[i].priority,
            };

            textAreaTeams.data = [...textAreaTeams.data, rowToAdd];
          }
          console.log(textAreaTeams);
          console.log("parsed json ", data);
        }.bind(this)
      )
      .catch(function (ex) {
        console.log("parsing failed", ex);
      });
  }

  _getMadMood() {
    fetch("http://localhost:9090/item/mad")
      .then(function (response) {
        return response.json();
      })
      .then(
        function (data) {
          // var textAreaTeams = this.shadowRoot.getElementById("myData");
          const textAreaTeams =
            this.shadowRoot.querySelector(".teamMad__table");
          for (var i = 0; i < data.length; i++) {
            var div = document.createElement("div");
            div.innerHTML = "Author: " + data[i].author;
            div.innerHTML = "Description: " + data[i].description;
            div.innerHTML = "Mood: " + data[i].mood;
            div.innerHTML = "Team Name: " + data[i].teamName;
            div.innerHTML = "Priority: " + data[i].priority;

            console.log(div);

            const rowToAdd = {
              col1: data[i].author,
              col2: data[i].description,
              col3: data[i].mood,
              col4: data[i].teamName,
              col5: data[i].priority,
            };

            textAreaTeams.data = [...textAreaTeams.data, rowToAdd];
          }
        }.bind(this)
      )
      .catch(function (ex) {
        console.log("parsing failed", ex);
      });
  }

  _getMembers() {
    fetch("http://localhost:9090/members")
      .then(function (response) {
        return response.json();
      })
      .then(
        function (data) {
          // var textAreaTeams = this.shadowRoot.getElementById("myData");
          const textAreaTeams = this.shadowRoot.querySelector(
            ".teamMembers__table"
          );
          for (var i = 0; i < data.length; i++) {
            var div = document.createElement("div");
            div.innerHTML = "Name: " + data[i].name;
            div.innerHTML = "Team Name: " + data[i].teamName;
            div.innerHTML = "Email: " + data[i].email;

            console.log(div);

            const rowToAdd = {
              col1: data[i].name,
              col2: data[i].teamName,
              col3: data[i].email,
            };

            textAreaTeams.data = [...textAreaTeams.data, rowToAdd];
          }
          console.log(textAreaTeams);
          console.log("parsed json ", data);
        }.bind(this)
      )
      .catch(function (ex) {
        console.log("parsing failed", ex);
      });
  }

  _getGladMood() {
    fetch("http://localhost:9090/item/glad")
      .then(function (response) {
        return response.json();
      })
      .then(
        function (data) {
          // var textAreaTeams = this.shadowRoot.getElementById("myData");
          const textAreaTeams =
            this.shadowRoot.querySelector(".teamGlad__table");
          for (var i = 0; i < data.length; i++) {
            var div = document.createElement("div");
            div.innerHTML = "Author: " + data[i].author;
            div.innerHTML = "Description: " + data[i].description;
            div.innerHTML = "Mood: " + data[i].mood;
            div.innerHTML = "Team Name: " + data[i].teamName;
            div.innerHTML = "Priority: " + data[i].priority;

            console.log(div);

            const rowToAdd = {
              col1: data[i].author,
              col2: data[i].description,
              col3: data[i].mood,
              col4: data[i].teamName,
              col5: data[i].priority,
            };

            textAreaTeams.data = [...textAreaTeams.data, rowToAdd];
          }
        }.bind(this)
      )
      .catch(function (ex) {
        console.log("parsing failed", ex);
      });
  }

   _getSadComment() {
    fetch("http://localhost:9090/item/comment/sad")
      .then(function (response) {
        return response.json();
      })
      .then(
        function (data) {
          // var textAreaTeams = this.shadowRoot.getElementById("myData");
          const textAreaTeams =
            this.shadowRoot.querySelector(".teamCommentSad__table");
          for (var i = 0; i < data.length; i++) {
            var div = document.createElement("div");
            div.innerHTML = "Author: " + data[i].author;
            //div.innerHTML = "Item: " + data[i].item;
            div.innerHTML = "Comment: " + data[i].comment;
            div.innerHTML = "Mood: " + data[i].mood;

            console.log(div);

            const rowToAdd = {
              col1: data[i].author,
              // col2: data[i].item,
              col2: data[i].comment,
              col3: data[i].mood,
            };

            textAreaTeams.data = [...textAreaTeams.data, rowToAdd];
          }
        }.bind(this)
      )
      .catch(function (ex) {
        console.log("parsing failed", ex);
      });
  }

  _getMadComment() {
    fetch("http://localhost:9090/item/comment/mad")
      .then(function (response) {
        return response.json();
      })
      .then(
        function (data) {
          // var textAreaTeams = this.shadowRoot.getElementById("myData");
          const textAreaTeams =
            this.shadowRoot.querySelector(".teamCommentMad__table");
          for (var i = 0; i < data.length; i++) {
            var div = document.createElement("div");
            div.innerHTML = "Author: " + data[i].author;
            //div.innerHTML = "Item: " + data[i].item;
            div.innerHTML = "Comment: " + data[i].comment;
            div.innerHTML = "Mood: " + data[i].mood;

            console.log(div);

            const rowToAdd = {
              col1: data[i].author,
              // col2: data[i].item,
              col2: data[i].comment,
              col3: data[i].mood,
            };

            textAreaTeams.data = [...textAreaTeams.data, rowToAdd];
          }
        }.bind(this)
      )
      .catch(function (ex) {
        console.log("parsing failed", ex);
      });
  }

  _getGladComment() {
    fetch("http://localhost:9090/item/comment/glad")
      .then(function (response) {
        return response.json();
      })
      .then(
        function (data) {
          // var textAreaTeams = this.shadowRoot.getElementById("myData");
          const textAreaTeams =
            this.shadowRoot.querySelector(".teamCommentGlad__table");
          for (var i = 0; i < data.length; i++) {
            var div = document.createElement("div");
            div.innerHTML = "Author: " + data[i].author;
            //div.innerHTML = "Item: " + data[i].item;
            div.innerHTML = "Comment: " + data[i].comment;
            div.innerHTML = "Mood: " + data[i].mood;

            console.log(div);

            const rowToAdd = {
              col1: data[i].author,
              // col2: data[i].item,
              col2: data[i].comment,
              col3: data[i].mood,
            };

            textAreaTeams.data = [...textAreaTeams.data, rowToAdd];
          }
        }.bind(this)
      )
      .catch(function (ex) {
        console.log("parsing failed", ex);
      });
  }

  _onChangeHandler(event, value) {
    if (value === "") {
      this.buttondisabled = true;
    } else {
      this.buttondisabled = false;
    }
  }

  _onChangeShow() {
    const showed = this.shadowRoot.querySelector("flyout-field");
  }

  _onButtonClicked(value) {
    fetch("/data")
      .then(function (response) {
        return response.json();
      })
      .then(
        function (json) {
          this.propTwo = json.data;
          console.log("parsed json ", json);
        }.bind(this)
      )
      .catch(function (ex) {
        console.log("parsing failed", ex);
      });
  }

  _lockBoard() {
    console.log("locking");
    const button1 = this.shadowRoot.querySelector("#teamButton");
    button1.disabled=true;
    const button2 = this.shadowRoot.querySelector("#memberButton");
    button2.disabled=true;
    const button3 = this.shadowRoot.querySelector("#taskButton");
    button3.disabled=true;
    const button4 = this.shadowRoot.querySelector("#commentButton");
    button4.disabled=true;
  }


  render() {
    const columns = [{ title: "Team Name", attribute: "col1" }];
    const data = [];

    const columnSad = [
      { title: "Author", attribute: "col1" },
      { title: "Description", attribute: "col2" },
      { title: "Mood", attribute: "col3" },
      { title: "Team Name", attribute: "col4" },
      { title: "Priority?", attribute: "col5" },
    ];
    const dataSad = [];

    const columnMad = [
      { title: "Author", attribute: "col1" },
      { title: "Description", attribute: "col2" },
      { title: "Mood", attribute: "col3" },
      { title: "Team Name", attribute: "col4" },
      { title: "Priority?", attribute: "col5" },
    ];
    const dataMad = [];

    const columnGlad = [
      { title: "Author", attribute: "col1" },
      { title: "Description", attribute: "col2" },
      { title: "Mood", attribute: "col3" },
      { title: "Team Name", attribute: "col4" },
      { title: "Priority?", attribute: "col5" },
    ];
    const dataGlad = [];

    const columnMember = [
      { title: "Name", attribute: "col1" },
      { title: "Team Name", attribute: "col2" },
      { title: "Email", attribute: "col3" },
    ];
    const dataMember = [];
    

    const columnCommentSad = [
      { title: "Author", attribute: "col1" },
      { title: "Comment", attribute: "col2" },
      // { title: "Item", attribute: "col3" },
      { title: "Mood", attribute: "col3" },
    ];
    const dataCommentSad = [];

    const columnCommentMad = [
      { title: "Author", attribute: "col1" },
      { title: "Comment", attribute: "col2" },
      // { title: "Item", attribute: "col3" },
      { title: "Mood", attribute: "col3" },
    ];
    const dataCommentMad = [];

    const columnCommentGlad = [
      { title: "Author", attribute: "col1" },
      { title: "Comment", attribute: "col2" },
      // { title: "Item", attribute: "col3" },
      { title: "Mood", attribute: "col3" },
    ];
    const dataCommentGlad = [];

    return html`
      <h1><center>Retro Tool</h1>

      <eui-layout-v0-card card-title="Teams" drag>
      value=${"Hi " + this._getTeams()}
      <div slot="content">
        <eui-table-v0 .columns=${columns} .data=${data}></eui-table-v0>
      </div>
      </eui-layout-v0-card>

      <eui-layout-v0-card card-title="Members" drag>
      value=${"Hi " + this._getMembers()}
      <div slot="content">
      <eui-table-v0 id=sadTable .columns=${columnMember} .data=${dataMember} class="teamMembers__table"></eui-table-v0>
      </div>
      </eui-layout-v0-card>

      <eui-layout-v0-card card-title="Sad" drag>
      value=${"Hi " + this._getSadMood()}
      <div slot="content">
      <eui-table-v0 id=sadTable .columns=${columnSad} .data=${dataSad} class="team__table">
      </eui-table-v0>
      </div>

      value=${"Hi " + this._getSadComment()}
      <div slot="content">
      <eui-table-v0 id=sadCommentTable .columns=${columnCommentSad} .data=${dataCommentSad} class="teamCommentSad__table"> 
      <eui-table-v0>
      </div>
      </eui-layout-v0-card>
      

      
      <eui-layout-v0-card card-title="Mad" drag>
      value=${"Hi " + this._getMadMood()}
      <div slot="content">
      <eui-table-v0 id=sadTable .columns=${columnMad} .data=${dataMad} class="teamMad__table" >
      </eui-table-v0>
      </div>
      value=${"Hi " + this._getMadComment()}
      <div slot="content">
      <eui-table-v0 id=sadCommentTable .columns=${columnCommentMad} .data=${dataCommentMad} class="teamCommentMad__table"> 
      <eui-table-v0>
      </div>
      </eui-layout-v0-card>
      

      <eui-layout-v0-card card-title="Glad" drag>
      value=${"Hi " + this._getGladMood()}
      <div slot="content">
      <eui-table-v0 id=sadTable .columns=${columnGlad} .data=${dataGlad} class="teamGlad__table">
      </eui-table-v0>
      </div>
      value=${"Hi " + this._getGladComment()}
      <div slot="content">
      <eui-table-v0 id=sadCommentTable .columns=${columnCommentGlad} .data=${dataCommentGlad} class="teamCommentGlad__table"> 
      <eui-table-v0>
      </div>
      </eui-layout-v0-card>
 

      <eui-layout-v0-card card-title="Create Menu">



      <div slot="content">
      <eui-base-v0-button id="teamButton" @click=${() => {
        this.teamShow = true;
      }}
        primary
        >
      Create Team
      </eui-base-v0-button>
      <div>

      <div slot="content">
      <br />
      <eui-base-v0-button id="memberButton" @click=${() => {
        this.memberShow = true;
      }}
        primary
        >
      Create Member
      </eui-base-v0-button>
      </div>

      <div slot="content">
      <br />
      <eui-base-v0-button id="taskButton" @click=${() => {
        console.log("create action clicked");
        this.show = true;
      }}
        primary
        >
      Create Task
      </eui-base-v0-button>
      <div>
      
      <div slot="content">
      <br />
      <eui-base-v0-button id="commentButton" @click=${() => {
        console.log("create action clicked");
        this.commentShow = true;
      }}
        primary
        >
      Create Comment
      </eui-base-v0-button>
      <div>



      <p></p>
      <div slot="content">
      <eui-base-v0-button @click=${() => {
        this._lockBoard();
      }}
        primary
        >
      Lock Board
      </eui-base-v0-button>
      <div>

      <p></p>
      <div slot="content">
      <eui-base-v0-button @click=${() => {
        this._deleteSprint();
      }}
        primary
        >
      Delete Board
      </eui-base-v0-button>
      <div>








      

      <eui-layout-v0-flyout-panel panel-title="Create Team" position="left"
      ?show="${this.teamShow}"
      >

      <div slot="content">
      <eui-base-v0-text-field
       id="team-entry"
       class="team__text-field"
       @keyup=${this}
       @input=${(event) =>
         this._onChangeHandler(event, event.currentTarget.value)}
       fullwidth>
       </div>
       <div slot="content">
       <eui-base-v0-button
       @click="${() => this._createTeam()}"
       primary
     >Create Team
    </eui-base-v0-button>
      </eui-base-v0-text-field>
      </div>

      <eui-base-v0-button primary slot="footer" @click=${() => {
        console.log("close action clicked");
        this.teamShow = false;
      }}>
        Close
        </eui-base-v0-button>
  
      </eui-layout-v0-flyout-panel>









      <eui-layout-v0-flyout-panel panel-title="Create member" 
      ?show="${this.memberShow}"
      >
      <div slot='content'>
      <eui-base-v0-text-field
      id="member-entry"
      class="member_name__text-field"
      placeholder=${"name"}
      @keyup=${this}
      @input=${(event) =>
        this._onChangeHandler(event, event.currentTarget.value)}
      
     </eui-base-v0-text-field>
     </div>
     <div slot="content">
     <eui-base-v0-text-field
      id="email-entry"
      class="member_email__text-field"
      placeholder=${"email"}
      @keyup=${this}
      @input=${(event) =>
        this._onChangeHandler(event, event.currentTarget.value)}
       
     </eui-base-v0-text-field>
     </div>
     
     <div slot="content">
     <eui-base-v0-text-field
      id="memberTeam-entry"
      class="member_teamname__text-field"
      placeholder=${"team"}
      @keyup=${this}
      @input=${(event) =>
        this._onChangeHandler(event, event.currentTarget.value)}
      
     </eui-base-v0-text-field>
     </div>
     <div slot="content">
     <br />
     <br />
     <eui-base-v0-button
        @click="${() => this._createMember()}"
        primary
      >Create Task
     </eui-base-v0-button>
     </div>

     <eui-base-v0-button primary slot="footer" @click=${() => {
       console.log("close action clicked");
       this.memberShow = false;
     }}>
      Close
      </eui-base-v0-button>

    </eui-layout-v0-flyout-panel>















    
 
      <eui-layout-v0-flyout-panel panel-title="Create your task" 
      ?show="${this.show}"
      >
      <div slot='content'>
      <eui-base-v0-text-field
      id="author-entry"
      class="item_author__text-field"
      placeholder=${"author"}
      @keyup=${this}
      @input=${(event) =>
        this._onChangeHandler(event, event.currentTarget.value)}
      
     </eui-base-v0-text-field>
     </div>
     <div slot="content">
     <eui-base-v0-text-field
      id="description-entry"
      class="item_description__text-field"
      placeholder=${"task description"}
      @keyup=${this}
      @input=${(event) =>
        this._onChangeHandler(event, event.currentTarget.value)}
       
     </eui-base-v0-text-field>
     </div>
     
     <div slot="content">
     <eui-base-v0-text-field
      id="mood-entry"
      class="item_mood__text-field"
      value="${this.mood}"
      placeholder=${"mood"}
      @keyup=${this}
      @input=${(event) =>
        this._onChangeHandler(event, event.currentTarget.value)}
      
     </eui-base-v0-text-field>
     </div>
     <div slot="content">
     <eui-base-v0-text-field
      id="teamname-entry"
      class="item_teamname__text-field"
      placeholder=${"team name"}
      @keyup=${this}
      @input=${(event) =>
        this._onChangeHandler(event, event.currentTarget.value)}
      
     </eui-base-v0-text-field>
      </div>

      <div slot="content">
      <eui-base-v0-text-field
       id="priority-entry"
       class="item_priority__text-field"
       value="${this.priority}"
       placeholder=${"Is this a priority?"}
       @keyup=${this}
       @input=${(event) =>
         this._onChangeHandler(event, event.currentTarget.value)}
       
      </eui-base-v0-text-field>
      </div>

      
      
      
      <div slot="content">
      <br />
      Is this a priority task?:
      <br />
      <br />
      <br />
       Yes
      <eui-v0-icon name="pin-solid" color='blue' @click=${() => {
        this.priority = "True";
      }}></eui-icon>
      </div>
      <div slot="content">
      No
      <eui-v0-icon name="cross" color='blue' @click=${() => {
        this.priority = "False";}}></eui-icon>
      </div>


      <div slot="content">
      <br />
      <br />
      <br />
      Choose your mood of task:
      <br />
      <br />
      <br />
      Glad
      <eui-v0-icon name="weather-sun" color='orange' @click=${() => {
        this.mood = "Glad";
      }}>
       </div>
       <div slot="content">
       <br />
       Sad
      <eui-v0-icon name="weather-rain" color='blue' @click=${() => {
        this.mood = "Sad";
      }}>
 
         </div>
         <div slot="content">
         <br />
         Mad
      <eui-v0-icon name="weather-storm" color='red' @click=${() => {
        this.mood = "Mad";
      }}>
     </div>
     <div slot="content">
     <br />
     <br />
     <br />
     <br />
     <br />
     <eui-base-v0-button
        @click="${() => this._createTask()}"
        primary
      >Create Task
     </eui-base-v0-button>
     </div>

     <eui-base-v0-button primary slot="footer" @click=${() => {
       console.log("close action clicked");
       this.show = false;
     }}>
      Close
      </eui-base-v0-button>

      </eui-layout-v0-flyout-panel>

      <eui-layout-v0-flyout-panel panel-title="Create Comments"
      ?show="${this.commentShow}"
      >
      <div slot='content'>
      <eui-base-v0-text-field
      id="author-entry"
      class="comment_author__text-field"
      placeholder=${"author"}
      @keyup=${this}
      @input=${(event) =>
        this._onChangeHandler(event, event.currentTarget.value)}
      
     </eui-base-v0-text-field>
     </div>
     <div slot="content">
     <eui-base-v0-text-field
      id="item-entry"
      class="comment_item__text-field"
      placeholder=${"item"}
      @keyup=${this}
      @input=${(event) =>
        this._onChangeHandler(event, event.currentTarget.value)}
       
     </eui-base-v0-text-field>
     </div>

      <div slot="content">
      <eui-base-v0-text-field
       id="comment-entry"
       class="comment__text-field"
       placeholder=${"Add comment"}
       @keyup=${this}
       @input=${(event) =>
         this._onChangeHandler(event, event.currentTarget.value)}>
       </div>
       <div slot="content">
       <eui-base-v0-text-field
        id="mood-entry"
        class="comment_mood__text-field"
        value="${this.commentMood}"
        placeholder=${"mood"}
        @keyup=${this}
        @input=${(event) =>
          this._onChangeHandler(event, event.currentTarget.value)}
        
       </eui-base-v0-text-field>
       </div>
       <div slot="content">
       <br />
       <br />
       <br />
       Choose your mood of task:
       <br />
       <br />
       Glad
       <eui-v0-icon name="weather-sun" color='orange' @click=${() => {
         this.commentMood = "Glad";
       }}>
        </div>
        <div slot="content">
        <br />
        Sad
       <eui-v0-icon name="weather-rain" color='blue' @click=${() => {
         this.commentMood = "Sad";
       }}>
  
          </div>
          <div slot="content">
          <br />
          Mad
       <eui-v0-icon name="weather-storm" color='red' @click=${() => {
         this.commentMood = "Mad";
       }}>
      </div>








       <div slot="content">
       <eui-base-v0-button
       @click="${() => this._createComment()}"
       primary
     >Create Comment
    </eui-base-v0-button>
      </eui-base-v0-text-field>
      </div>

      
      <eui-base-v0-button primary slot="footer" @click=${() => {
        this.commentShow = false;
      }}>
        Close
        </eui-base-v0-button>
  
      </eui-layout-v0-flyout-panel>
 
      `;
  }
}

MyComponent.register();
