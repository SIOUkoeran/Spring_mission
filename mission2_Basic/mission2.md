2.
# BASIC

## 테스트 화면

<img width="720" alt="스크린샷 2022-02-22 오후 11 47 40" src="https://user-images.githubusercontent.com/35398494/155157387-bbfb8081-8de1-406e-95e6-810fa0eecafe.png"/>

## api

<body class="book toc2 toc-left">
<div id="header">
<h1>API Guide</h1>
<div class="details">
<span id="revnumber">version 0.0.1-SNAPSHOT</span>
</div>
<div id="toc" class="toc2">
<div id="toctitle">Table of Contents</div>
<ul class="sectlevel1">
<li><a href="#api">1. 게시판(Board)</a>
<ul class="sectlevel2">
<li><a href="#_게시판_생성">1.1. 게시판 생성</a></li>
<li><a href="#_게시판_수정">1.2. 게시판 수정</a></li>
<li><a href="#_게시판_검색">1.3. 게시판 검색</a></li>
<li><a href="#_게시판_삭제">1.4. 게시판 삭제</a></li>
</ul>
</li>
<li><a href="#_게시물post">2. 게시물(Post)</a>
<ul class="sectlevel2">
<li><a href="#_게시물_생성">2.1. 게시물 생성</a></li>
<li><a href="#_게시물_수정">2.2. 게시물 수정</a></li>
<li><a href="#_게시판_별_게시물들_검색">2.3. 게시판 별 게시물들 검색</a></li>
<li><a href="#_게시물_삭제">2.4. 게시물 삭제</a></li>
</ul>
</li>
</ul>
</div>
</div>
<div id="content">
<div class="sect1">
<h2 id="api"><a class="anchor" href="#api"></a><a class="link" href="#api">1. 게시판(Board)</a></h2>
<div class="sectionbody">
<div class="sect2">
<h3 id="_게시판_생성"><a class="anchor" href="#_게시판_생성"></a><a class="link" href="#_게시판_생성">1.1. 게시판 생성</a></h3>
<div class="paragraph">
<p><code>Post</code> 요청을 사용해서 새로운 게시판 생성 가능</p>
</div>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>title</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">board title</p></td>
</tr>
</tbody>
</table>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code data-lang="bash" class="language-bash hljs">$ curl 'http://localhost:8080/api/board/create' -i -X POST \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -H 'Accept: application/json' \
    -d '{"title":"sampleBoard"}'</code></pre>
</div>
</div>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Content-Type</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">http content header</p></td>
</tr>
</tbody>
</table>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code data-lang="http" class="language-http hljs">HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 94

{"code":"2010","data":{"boardId":1,"title":"sampleBoard","posts":[]},"message":"CREATE_BOARD"}</code></pre>
</div>
</div>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Object</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response data</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>message</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response message</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>code</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response custom code (HttpStatus is always 200. so, code is real status code)</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data.title</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">created board title</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data.boardId</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">created board id</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data.posts</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Array</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">created board post id list</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect2">
<h3 id="_게시판_수정"><a class="anchor" href="#_게시판_수정"></a><a class="link" href="#_게시판_수정">1.2. 게시판 수정</a></h3>
<div class="paragraph">
<p><code>Put</code> 요청을 사용해서 새로운 게시판 생성 가능</p>
</div>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>title</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">board title</p></td>
</tr>
</tbody>
</table>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code data-lang="bash" class="language-bash hljs">$ curl 'http://localhost:8080/api/board/update/1' -i -X PUT \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -H 'Accept: application/json' \
    -d '{"title":"updateTitle"}'</code></pre>
</div>
</div>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Content-Type</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">reqeust http content header</p></td>
</tr>
</tbody>
</table>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code data-lang="http" class="language-http hljs">HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 94

{"code":"2000","data":{"boardId":1,"title":"updateTitle","posts":[]},"message":"UPDATE_BOARD"}</code></pre>
</div>
</div>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Object</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response data</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>message</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response message</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>code</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response custom code (HttpStatus is always 200. so, code is real status code)</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data.title</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">revised board title</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data.boardId</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">revised board id</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data.posts</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Array</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">revised board post id list</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect2">
<h3 id="_게시판_검색"><a class="anchor" href="#_게시판_검색"></a><a class="link" href="#_게시판_검색">1.3. 게시판 검색</a></h3>
<div class="paragraph">
<p><code>Get</code> 요청을 사용해서 새로운 게시판 생성 가능</p>
</div>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code data-lang="bash" class="language-bash hljs">$ curl 'http://localhost:8080/api/board/1' -i -X GET \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -H 'Accept: application/json'</code></pre>
</div>
</div>
<table class="tableblock frame-all grid-all stretch">
<caption class="title">Table 1. /api/board/{boardId}</caption>
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Parameter</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>boardId</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">id of the post you want to find</p></td>
</tr>
</tbody>
</table>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Content-Type</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">reqeust http content header</p></td>
</tr>
</tbody>
</table>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code data-lang="http" class="language-http hljs">HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 85

{"code":"2000","data":{"boardId":1,"title":"title","posts":[]},"message":"GET_BOARD"}</code></pre>
</div>
</div>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Object</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response data</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>message</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response message</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>code</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response custom code (HttpStatus is always 200. so, code is real status code)</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data.title</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">board title</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data.boardId</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">board id</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data.posts</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Array</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">board post id list</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect2">
<h3 id="_게시판_삭제"><a class="anchor" href="#_게시판_삭제"></a><a class="link" href="#_게시판_삭제">1.4. 게시판 삭제</a></h3>
<div class="paragraph">
<p><code>Delete</code> 요청을 사용해서 새로운 게시판 생성 가능</p>
</div>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code data-lang="bash" class="language-bash hljs">$ curl 'http://localhost:8080/api/board/delete/1' -i -X DELETE \
    -H 'Accept: application/json'</code></pre>
</div>
</div>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code data-lang="http" class="language-http hljs">HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 52

{"code":"2000","data":null,"message":"DELETE_BOARD"}</code></pre>
</div>
</div>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Null</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response data</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>message</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response message</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>code</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response custom code (HttpStatus is always 200. so, code is real status code)</p></td>
</tr>
</tbody>
</table>
</div>
</div>
</div>
<div class="sect1">
<h2 id="_게시물post"><a class="anchor" href="#_게시물post"></a><a class="link" href="#_게시물post">2. 게시물(Post)</a></h2>
<div class="sectionbody">
<div class="sect2">
<h3 id="_게시물_생성"><a class="anchor" href="#_게시물_생성"></a><a class="link" href="#_게시물_생성">2.1. 게시물 생성</a></h3>
<div class="paragraph">
<p><code>Post</code> 요청을 사용해서 새로운 게시물 생성 가능</p>
</div>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>title</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">post title</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>writer</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">post writer</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>password</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">post password</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>boardId</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Null</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">post board Id</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>content</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">post content</p></td>
</tr>
</tbody>
</table>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code data-lang="bash" class="language-bash hljs">$ curl 'http://localhost:8080/api/board/1/post/create' -i -X POST \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -H 'Accept: application/json' \
    -d '{"id":null,"title":"postTitle","writer":"writer","password":"password1","content":"content","boardId":null}'</code></pre>
</div>
</div>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Content-Type</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">request http content header</p></td>
</tr>
</tbody>
</table>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code data-lang="http" class="language-http hljs">HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 129

{"code":"2010","data":{"title":"postTitle","writer":"writer","content":"content","postId":1,"boardId":1},"message":"CREATE_POST"}</code></pre>
</div>
</div>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Object</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response data</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>message</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response message</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>code</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response custom code (HttpStatus is always 200. so, code is real status code)</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data.postId</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">created post id</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data.writer</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">created post writer</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data.title</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">created post title</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data.content</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">created post content</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data.boardId</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">created post board id</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect2">
<h3 id="_게시물_수정"><a class="anchor" href="#_게시물_수정"></a><a class="link" href="#_게시물_수정">2.2. 게시물 수정</a></h3>
<div class="paragraph">
<p><code>Put</code> 요청을 사용해서 새로운 게시물 생성 가능</p>
</div>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>title</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">post title</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>writer</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">post writer</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>password</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">post password</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>boardId</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Null</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">post board Id</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>content</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">post content</p></td>
</tr>
</tbody>
</table>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code data-lang="bash" class="language-bash hljs">$ curl 'http://localhost:8080/api/board/1/post/create' -i -X POST \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -H 'Accept: application/json' \
    -d '{"id":null,"title":"postTitle","writer":"writer","password":"password1","content":"content","boardId":null}'</code></pre>
</div>
</div>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Content-Type</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">request http content header</p></td>
</tr>
</tbody>
</table>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code data-lang="http" class="language-http hljs">HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 129

{"code":"2010","data":{"title":"postTitle","writer":"writer","content":"content","postId":1,"boardId":1},"message":"CREATE_POST"}</code></pre>
</div>
</div>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Object</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response data</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>message</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response message</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>code</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response custom code (HttpStatus is always 200. so, code is real status code)</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data.postId</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">created post id</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data.writer</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">created post writer</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data.title</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">created post title</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data.content</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">created post content</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data.boardId</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">created post board id</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect2">
<h3 id="_게시판_별_게시물들_검색"><a class="anchor" href="#_게시판_별_게시물들_검색"></a><a class="link" href="#_게시판_별_게시물들_검색">2.3. 게시판 별 게시물들 검색</a></h3>
<div class="paragraph">
<p><code>Get</code> 요청을 사용해서 게시판 별 게시물들 받아오기</p>
</div>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code data-lang="bash" class="language-bash hljs">$ curl 'http://localhost:8080/api/board/1/post' -i -X GET \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -H 'Accept: application/json'</code></pre>
</div>
</div>
<table class="tableblock frame-all grid-all stretch">
<caption class="title">Table 2. /api/board/{boardId}/post</caption>
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Parameter</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>boardId</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">request board id</p></td>
</tr>
</tbody>
</table>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Content-Type</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">request http content header</p></td>
</tr>
</tbody>
</table>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code data-lang="http" class="language-http hljs">HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 391

{"code":"2000","data":[{"title":"postTitle 1","writer":"writer","content":"content","postId":1,"boardId":1},{"title":"postTitle 2","writer":"writer","content":"content","postId":2,"boardId":1},{"title":"postTitle 3","writer":"writer","content":"content","postId":3,"boardId":1},{"title":"postTitle 4","writer":"writer","content":"content","postId":4,"boardId":1}],"message":"POSTS_BY_BOARD"}</code></pre>
</div>
</div>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Array</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response data</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>message</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response message</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>code</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response custom code (HttpStatus is always 200. so, code is real status code)</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data[].postId</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">found post writer by board id</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data[].writer</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">found post writer by board id</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data[].title</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">found post writer by board id</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data[].content</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">found post writer by board id</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data[].boardId</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">found post writer by board id</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect2">
<h3 id="_게시물_삭제"><a class="anchor" href="#_게시물_삭제"></a><a class="link" href="#_게시물_삭제">2.4. 게시물 삭제</a></h3>
<div class="paragraph">
<p><code>Delete</code> 요청을 사용해서 새로운 게시물 생성 가능</p>
</div>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code data-lang="bash" class="language-bash hljs">$ curl 'http://localhost:8080/api/post/delete' -i -X DELETE \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -H 'Accept: application/json' \
    -d '{"id":1,"title":"postTitle","writer":"writer","password":"password1","content":"content","boardId":null}'</code></pre>
</div>
</div>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Content-Type</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">request http content header</p></td>
</tr>
</tbody>
</table>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code data-lang="http" class="language-http hljs">HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 51

{"code":"2000","data":null,"message":"DELETE_POST"}</code></pre>
</div>
</div>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>data</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Null</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response data</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>message</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response message</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>code</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response custom code (HttpStatus is always 200. so, code is real status code)</p></td>
</tr>
</tbody>
</table>
</div>
</div>
</div>
</div>
<div id="footer">
<div id="footer-text">
Version 0.0.1-SNAPSHOT<br>
Last updated 2022-02-22 23:43:48 +0900
</div>
</div>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.6/styles/github.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.6/highlight.min.js"></script>
<script>hljs.initHighlighting()</script>
</body>



## CHALLENGE

