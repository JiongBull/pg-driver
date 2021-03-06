/*
 * Copyright 2016-2017 HiveBox.
 */

package com.fcbox.pangu.driver;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import rx.Observable;

/**
 * 控制台接口, 供应商需要实现该接口.
 */
@SuppressWarnings("ALL")
public interface IConsole {

    /**
     * 获取版本信息.
     *
     * @return 版本信息
     */
    Observable<Version> getVersion();

    /**
     * 读取单柜编码.
     *
     * @return 单柜编码列表
     */
    Observable<List<String>> readPortionCodes();

    /**
     * 激活柜机.
     *
     * @param portionCodes 单柜编码列表
     */
    Observable<Void> activateLocker(@NonNull List<String> portionCodes);

    /**
     * 获取柜机外部的温度数值.
     *
     * @return 柜机外部的温度数值，摄氏度
     */
    Observable<Integer> getLockerTemperature();

    /**
     * 获取柜机外部的湿度数值.
     *
     * @return 柜机外部的湿度数值，百分制
     */
    Observable<Integer> getLockerHumidity();

    /**
     * 打开单个格口.
     *
     * @param cellCode 格口编码
     * @return 格口的开关状态，true - 开启状态，false - 关闭状态
     */
    Observable<Boolean> openCell(@NonNull String cellCode);

    /**
     * 批量打开格口.
     *
     * @param cellCodes 格口编码列表
     * @return 格口的开关状态列表，true - 开启状态，false - 关闭状态
     */
    Observable<List<Boolean>> openCells(@NonNull List<String> cellCodes);

    /**
     * 读取格口的开关状态.
     *
     * @param cellCodes 格口编码列表
     * @return 格口的开关状态列表，true - 开启状态，false - 关闭状态
     */
    Observable<List<Boolean>> readCellStatuses(@NonNull List<String> cellCodes);

    /**
     * /**
     * 格口红外检测.
     *
     * @param cellCodes 格口编码列表
     * @return 格口的储物状态列表，true - 占用状态，false - 空闲状态
     */
    Observable<List<Boolean>> cellsInfraredDetection(@NonNull List<String> cellCodes);

    /**
     * 格口快照.
     *
     * @param dirPath   快照存储的目录路径
     * @param cellCodes 格口编码列表
     * @return 快照的路径列表
     */
    Observable<List<String>> cellsSnapshot(@NonNull String dirPath, @NonNull List<String> cellCodes);

    /**
     * 开启RFID的识别.
     *
     * @param duration 持续时间
     * @return 识别到的数据
     */
    Observable<String> startRfid(long duration);

    /**
     * 关闭RFID的识别.
     */
    Observable<Void> stopRfid();

    /**
     * 开启条码扫描器的识别.
     *
     * @param duration 持续时间
     * @return 扫描到的数据
     */
    Observable<String> startScanner(long duration);

    /**
     * 关闭条码扫描器的识别.
     */
    Observable<Void> stopScanner();

    /**
     * 控制灯箱开关.
     *
     * @param io 开/关
     */
    Observable<Void> light(@IO int io);

    /**
     * 静默安装指定路径的apk，安装时前台界面显示升级提示页面，安装完成后关闭升级提示页面，
     * 根据传递的包名和类名检测组件是activity还是service来启动对应的组件，包名和类名为空时不做任何操作.
     *
     * @param apkPath apk的路径
     * @param pkgName 全限定包名
     * @param clsName 全限定类名
     */
    Observable<Void> installApk(@NonNull String apkPath, @Nullable String pkgName, @Nullable String clsName);
}

