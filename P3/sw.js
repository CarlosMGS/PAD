if(!self.define){const e=e=>{"require"!==e&&(e+=".js");let r=Promise.resolve();return s[e]||(r=new Promise(async r=>{if("document"in self){const s=document.createElement("script");s.src=e,document.head.appendChild(s),s.onload=r}else importScripts(e),r()})),r.then(()=>{if(!s[e])throw new Error(`Module ${e} didn’t register its module`);return s[e]})},r=(r,s)=>{Promise.all(r.map(e)).then(e=>s(1===e.length?e[0]:e))},s={require:Promise.resolve(r)};self.define=(r,i,c)=>{s[r]||(s[r]=Promise.resolve().then(()=>{let s={};const o={uri:location.origin+r.slice(1)};return Promise.all(i.map(r=>{switch(r){case"exports":return s;case"module":return o;default:return e(r)}})).then(e=>{const r=c(...e);return s.default||(s.default=r),s})}))}}define("./sw.js",["./workbox-1bbb3e0e"],(function(e){"use strict";self.addEventListener("message",e=>{e.data&&"SKIP_WAITING"===e.data.type&&self.skipWaiting()}),e.precacheAndRoute([{url:"addtarea.js",revision:"5d4d21464b30cb64f4c294e8b77f085b"},{url:"app.js",revision:"b9d9775f462e234699d8968218919dcb"},{url:"index.html",revision:"5e9329d7c9c4abfe4888a42277dfd8f9"},{url:"manifest.json",revision:"96ad9bbc353640935581760e649a89c2"},{url:"mostrartareas.js",revision:"6b70894cb0cd14a77894587d263da48d"},{url:"package-lock.json",revision:"d3b96d889cc0de0d4baa8e25951661de"},{url:"package.json",revision:"4a23f4368a71420b64a81ab3c6b72626"},{url:"server.js",revision:"192f5cb5953e7905950f7722e88d6f84"},{url:"serviceworker.js",revision:"d41d8cd98f00b204e9800998ecf8427e"},{url:"style.css",revision:"c80784d7cb81f7bdbba08d8a19ea250a"},{url:"tarea.js",revision:"1dc290d51c43533dec657f522be07c61"}],{})}));
//# sourceMappingURL=sw.js.map
