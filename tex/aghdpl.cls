%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% 
% File:          aghdpl.cls         (LaTeX document class option "aghdpl")
%          
% Author:        Marcin Szpyrka     (mszpyrka@agh.edu.pl)
%                Grzegorz J. Nalepa (gjn@agh.edu.pl)
%                AGH University of Science and Technology, Kraków, POLAND
%
% Modifications: Jarosław Waliszko  (jaroslaw.waliszko@gmail.com) 
%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

\message{*** [aghdpl.cls] ---  (MSz, GJN)  v0.1 <03.03.2009> [modified by jwa] ***}       

\newif\ifpdflatex
\pdflatexfalse

\newif\ifletterpaper
\letterpapertrue % for submission to arXiv letterpaper is preferred over a4paper, i.e. \letterpaperfalse

\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{aghdpl}
\DeclareOption{pdflatex}{\pdflatexfalse}
\DeclareOption*{\PassOptionsToClass{\CurrentOption}{report}}

\ifletterpaper
	\ProcessOptions\LoadClass[letterpaper,twoside]{report}
\else
	\ProcessOptions\LoadClass[a4paper,twoside]{report}
\fi

\RequirePackage{times}
\RequirePackage[T1]{fontenc}
\RequirePackage[utf8]{inputenc}
\RequirePackage{graphicx}
\RequirePackage{geometry}
\RequirePackage[usenames]{color}
\RequirePackage{fancyhdr}
\RequirePackage[obeyspaces]{url}
\RequirePackage{hyperref}
\RequirePackage[noindentafter]{titlesec}
\RequirePackage[nottoc,chapter]{tocbibind} % add bibliography to ToC and remove Contents self-reference from ToC
\RequirePackage{tocloft}

\ifletterpaper
	\geometry{lmargin=30mm, rmargin=20mm, tmargin=25mm, bmargin=25mm, foot=10mm, head=10mm} % letterpaper
\else
	\geometry{lmargin=27mm, rmargin=17mm, tmargin=33mm, bmargin=33mm, foot=10mm, head=10mm} % a4paper
\fi

\definecolor{Green}{rgb}{0.1,0.5,0.1}

\def\shorttitle#1         {\gdef\@shorttitle{#1}}
\def\shortauthor#1        {\gdef\@shortauthor{#1}}
\def\email#1              {\gdef\@email{#1}}
\def\title#1              {\gdef\@title{#1}}
\def\faculty#1            {\gdef\@faculty{#1}}
\def\department#1         {\gdef\@department{#1}}
\def\degreeprogramme#1    {\gdef\@degreeprogramme{#1}}
\def\thesistype#1         {\gdef\@thesistype{#1}}
\def\supervisor#1         {\gdef\@supervisor{#1}}
\def\acknowledgements#1   {\gdef\@acknowledgements{#1}}
\def\thesiskeywords#1     {\gdef\@thesiskeywords{#1}}

\AtBeginDocument{
  \hypersetup{
    breaklinks,
    pdftitle={\@title},
    pdfauthor={\@author},
    pdfsubject={\@thesistype},
    pdfkeywords={\@thesiskeywords},
    linktocpage=true, % only the page numbers in ToC are hyperlinked
    linkbordercolor=red, % hyperlink borders will be red
    pdfborderstyle={/S/U/W 1} % border style will be underline of width 1pt
  }
}
% good hypersetup alternatives:
%   hidelinks=true, linktoc=all
%   or, colorlinks=false, linktocpage=true

\linespread{1.3} % "one and a half" line spacing (value 1.6 refers to "double line spacing")
\bibliographystyle{plain}

% -----------------------------------------------------------------------------------------

\newcommand{\titlepages}{

\newpage
\titlepage 

\begin{center}

{\large\bfseries\scshape AGH University of Science and Technology}\vspace*{1mm}

{\small\bfseries\scshape Akademia Górniczo-Hutnicza im. Stanisława Staszica w Krakowie}\vspace*{1mm}

\rule\textwidth{1mm}\vspace*{1mm}

{\small\scshape \@faculty}\vspace*{1mm}

{\small\scshape \@department}\vspace*{1cm}

\includegraphics[scale=0.2]{agh}\vspace*{2cm}

{\huge\scshape \@title}\vspace*{2cm}

{\large\scshape \@thesistype}\vspace*{2cm}

{\LARGE\scshape \@author}\vspace*{5mm}

{\large\scshape Krakow \@date}\vfill

{\large\scshape 
\begin{tabular}{p{45mm}l}
Degree programme: & {\@degreeprogramme}\\[-1mm]
Supervisor: & {\@supervisor}\\
\end{tabular}
}

\end{center}

\clearpage
\titlepage 
\vspace*{15cm}\vfill

\begin{flushright}
\begin{minipage}[!h]{12cm}
\mbox{\large\scshape \textbf{Declaration of originality}}\\[3mm]
\large\itshape I hereby declare, aware of criminal liability for false declaration, that this thesis represents my original work. I am the sole author and did not use sources other than those referenced.\\
\end{minipage}

\includegraphics[height=1.5\baselineskip]{signature}

{\large\scshape \@author}

\end{flushright}

\clearpage
\titlepage
\vspace*{15cm}\vfill

\begin{flushright} 
\begin{minipage}[!h]{12cm}
\mbox{\large\scshape \textbf{Acknowledgments}}\\[3mm]
{\large\itshape \@acknowledgements}
\end{minipage}
\end{flushright}

\clearpage
\thispagestyle{empty}
\vspace*{15cm}\vfill
\clearpage

\setcounter{page}{5}

}

\def\thesisheaders{
\fancyfoot[R]{\small \@shortauthor\quad\textit{\@shorttitle}}
}

\DeclareOption{en}{
\def\thesisheaders{
\fancyfoot[LE,RO]{\small \@shortauthor\quad\textit{\@shorttitle}}
}
}

\ProcessOptions

\pagestyle{fancy}
\renewcommand{\chaptermark}[1]{\markboth{\thechapter\quad\ #1}{}}
\renewcommand{\sectionmark}[1]{\markright{\thesection\quad\ #1}}
\renewcommand{\headrulewidth}{0.5pt}
\renewcommand{\footrulewidth}{0.5pt}
\fancyhf{}
\fancyhead{}
\fancyhead[RE,LO]{\small\slshape \rightmark}
\fancyhead[RO,LE]{\bfseries \thepage}

\thesisheaders

\frenchspacing
\sloppy
\widowpenalty     9999
\clubpenalty      9999
\flushbottom

% -------------- parameters settings for titles of chapters, sections, etc. ---------------

\titleformat{\section}[block]
{\bfseries\Large}{\filright \Large\thesection\quad}{0ex}{}
\titlespacing{\section}{7mm}{8mm plus 0mm minus 1mm}{4mm plus 0mm minus 1mm}

\titleformat{\subsection}[block]
{\bfseries\large}{\filright \large\thesubsection\quad}{0ex}{}
\titlespacing{\subsection}{7mm}{6mm}{2.5mm}

\titleformat{\subsubsection}[block]
{\bfseries\normalsize}{\filright \normalsize\thesubsubsection\quad}{0ex}{}
\titlespacing{\subsubsection}{7mm}{3mm}{1.5mm}
