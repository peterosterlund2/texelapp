LOCAL_PATH := $(call my-dir)

TX_SRC	:= bitBoard.cpp book.cpp computerPlayer.cpp enginecontrol.cpp endGameEval.cpp evaluate.cpp \
	   game.cpp history.cpp humanPlayer.cpp killerTable.cpp kpkTable.cpp \
	   krkpTable.cpp krpkrTable.cpp material.cpp move.cpp moveGen.cpp numa.cpp parameters.cpp \
	   parallel.cpp piece.cpp position.cpp search.cpp tbprobe.cpp texel.cpp textio.cpp \
	   transpositionTable.cpp treeLogger.cpp tuigame.cpp uciprotocol.cpp \
	   util/logger.cpp util/random.cpp util/timeUtil.cpp util/util.cpp \
	   syzygy/rtb-probe.cpp

TX_SRC_GTB := gtb/compression/lzma/Lzma86Dec.c gtb/compression/lzma/LzFind.c \
	      gtb/compression/lzma/Lzma86Enc.c gtb/compression/lzma/LzmaDec.c \
	      gtb/compression/lzma/Alloc.c gtb/compression/lzma/Bra86.c \
	      gtb/compression/lzma/LzmaEnc.c gtb/compression/wrap.c gtb/gtb-dec.c \
	      gtb/gtb-att.c gtb/sysport/sysport.c gtb/gtb-probe.c

TX_INC := -I$(LOCAL_PATH)/gtb/sysport -I$(LOCAL_PATH)/gtb/compression \
	  -I$(LOCAL_PATH)/gtb/compression/lzma -I$(LOCAL_PATH)/syzygy

include $(CLEAR_VARS)
LOCAL_MODULE    := texel
LOCAL_SRC_FILES := $(TX_SRC) $(TX_SRC_GTB)
LOCAL_CFLAGS    := -O3 -fPIE $(TX_INC) -DHAS_CTZ -DHAS_PREFETCH -DHAS_RT
LOCAL_CPPFLAGS  := -std=c++11
LOCAL_LDFLAGS	+= -fPIE -pie
LOCAL_CPP_FEATURES := rtti exceptions
include $(BUILD_EXECUTABLE)
