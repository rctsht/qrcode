import React, { Component } from "react";
import {
  processColor,
  ProcessedColorValue,
  requireNativeComponent,
} from "react-native";

type NativeProps = {
  color: ProcessedColorValue | null | undefined;
  data: string;
  errorCorrectionLevel: ErrorCorrectionLevel;
};

const RSQrCodeView = requireNativeComponent<NativeProps>("RSQrCodeView");

export enum ErrorCorrectionLevel {
  L = "L",
  M = "M",
  Q = "Q",
  H = "H",
}

type Props = {
  color: string;
  data: string;
  errorCorrectionLevel: ErrorCorrectionLevel;
};

export class QRCode extends Component<Props> {
  render() {
    const { color, ...rest } = this.props;

    const processedColor = processColor(color);

    return <RSQrCodeView {...rest} color={processedColor} />;
  }
}
