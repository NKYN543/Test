import { Typography, Button, Box } from "@mui/material";
import { useNavigate } from "react-router-dom";

export default function NotFoundPage() {
    const navigate = useNavigate();

    return (
        <Box
            sx={{
                height: "100vh",
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "center",
                bgcolor:   "grey.100",
                color:  "text.secondary",
                textAlign: "center",
                padding: 4,
            }}
        >
            <Typography
                variant="h1"
                sx={{
                    fontSize: "6rem",
                    fontWeight: 700,
                    marginBottom: 2,
                }}
            >
                404
            </Typography>
            <Typography
                variant="h6"
                sx={{
                    marginBottom: 4,
                }}
            >
                Oops! The page you're looking for doesn't exist.
            </Typography>
            <Button
                variant="contained"
                color="primary"
                onClick={() => navigate("/")}
                sx={{
                    textTransform: "none",
                }}
            >
                返回首页
            </Button>
        </Box>
    );
}
