import { Modal, View, Text, SafeAreaView, ModalProps } from "react-native";
import { useTheme } from "../../theme";
import { IconButton } from "../IconButton";

type Props = ModalProps & {
  children?: React.ReactNode;
  onClose: () => void;
};

export const FullscreenModal = ({ children, onClose, ...props }: Props) => {
  const { theme } = useTheme();

  return (
    <Modal {...props} transparent animationType="fade">
      <View
        style={{
          flex: 1,
          justifyContent: "center",
          alignItems: "center",
          backgroundColor: "rgba(0, 0, 0, 0.75)",
          padding: theme.spacing.$5,
        }}
      >
        <SafeAreaView
          style={{
            flex: 1,
            alignSelf: "stretch",
          }}
        >
          <View
            style={{
              backgroundColor: theme.colors.surfaceLight,
              borderRadius: theme.radii.$4,
              padding: theme.spacing.$5,
              borderWidth: 2,
              borderColor: theme.colors.text,
              flex: 1,
              alignSelf: "stretch",
            }}
          >
            {children}
            <View style={{ marginTop: "auto" }} />
            <IconButton onPress={onClose} icon="close" noShadow>
              Close
            </IconButton>
          </View>
        </SafeAreaView>
      </View>
    </Modal>
  );
};
