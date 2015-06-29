<?php
    header("Status: 301 Moved Permanently");
    header("Location:"
		. "xwiki.ipad.com.br_8027/xwiki/bin/view/AjudaGSAN/"
		. $_SERVER['QUERY_STRING']);
    exit;
