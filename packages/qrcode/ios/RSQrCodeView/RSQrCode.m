#import "UIView+React.h"
#import <CoreImage/CIFilterBuiltins.h>
#import "RSQrCode.h"

@implementation RSQrCode

@synthesize color = _color;
@synthesize data = _data;
@synthesize errorCorrectionLevel = _errorCorrectionLevel;

-(id)init {
  if (self = [super init])
  _errorCorrectionLevel = @"M";
  return self;
}

-(void)setColor:(UIColor *)color
{
  _color = [[CIColor alloc] initWithColor:color];
  [self setNeedsLayout];
}

-(void)setData:(NSData *)data
{
  _data = data;
  [self setNeedsLayout];
}

-(void)setErrorCorrectionLevel:(NSString *)errorCorrectionLevel
{
  _errorCorrectionLevel = errorCorrectionLevel;
  [self setNeedsLayout];
}

- (UIImage *)generateQrCode {
  UIImage *image = nil;

  @try {
    CIImage *ciImage = nil;

    if (!self.data || self.data == nil || self.data.length <= 0) {
      return nil;
    }

    CIFilter *qrCodeFilter = [CIFilter QRCodeGenerator];

    if (qrCodeFilter == nil) {
      return nil;
    }

    [qrCodeFilter setDefaults];
    [qrCodeFilter setValue:self.data forKey:@"inputMessage"];

    if (_errorCorrectionLevel != nil) {
      [qrCodeFilter setValue:self.errorCorrectionLevel forKey:@"inputCorrectionLevel"];
    }

    CIFilter *colorFilter = [CIFilter filterWithName:@"CIFalseColor"];

    if (colorFilter == nil) {
      return nil;
    }

    [colorFilter setDefaults];
    [colorFilter setValue:qrCodeFilter.outputImage forKey:@"inputImage"];
    [colorFilter setValue:self.color forKey:@"inputColor0"];
    [colorFilter setValue:CIColor.clearColor forKey:@"inputColor1"];

    ciImage = colorFilter.outputImage;

    if (!ciImage || ciImage == nil) {
      return nil;
    }

    CGRect imageSize = CGRectIntegral(ciImage.extent);
    CGSize outputSize = self.size;

    CGFloat width = CGRectGetWidth(imageSize);
    CGFloat height = CGRectGetHeight(imageSize);
    CGAffineTransform scale = CGAffineTransformMakeScale(outputSize.width/width, outputSize.height/height);

    CIImage *imageByTransform = [ciImage imageByApplyingTransform:scale];

    image = [UIImage imageWithCIImage:imageByTransform];
  } @catch (NSException *exception) {
    NSLog(@"Error: %@", exception);
  }

  return image;
}

- (void)layoutSubviews
{
  [super layoutSubviews];

  CGFloat width = self.frame.size.width;
  CGFloat height = self.frame.size.height;

  self.size = CGSizeMake(width,height);

  UIImage *image = [self generateQrCode];
  UIImageView *imageView = [[UIImageView alloc] initWithImage:image];

  imageView.frame = CGRectMake(0,0,width,height);

  NSArray *viewsToRemove = [self subviews];

  for (UIView *view in viewsToRemove) {
    [view removeFromSuperview];
  }

  [self addSubview:imageView];
}

@end
